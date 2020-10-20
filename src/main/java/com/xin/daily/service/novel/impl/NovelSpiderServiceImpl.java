package com.xin.daily.service.novel.impl;

import com.xin.daily.common.consts.CommonConst;
import com.xin.daily.dao.MybatisUtils;
import com.xin.daily.dao.novel.NovelChapterMapper;
import com.xin.daily.dao.novel.NovelMapper;
import com.xin.daily.entity.novel.Novel;
import com.xin.daily.entity.novel.NovelChapter;
import com.xin.daily.service.novel.INovelSpiderService;
import com.xin.daily.service.novel.analyzer.NovelDocumentAnalyzer;
import com.xin.daily.vo.NovelChapterVo;
import com.xin.web.base.BaseService;
import com.xin.web.pojo.Context;
import com.xin.common.utils.crypt.SnowFlake;
import com.xin.web.utils.jsoup.JsoupUtils;
import com.xin.common.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * 小说抓取service实现类
 *
 * @author creator mafh 2019/12/2 22:02
 * @author updater
 * @version 1.0.0
 */
@Service
@Transactional
public class NovelSpiderServiceImpl extends BaseService implements INovelSpiderService {

    /**
     * 小说数据映射
     */
    @Autowired
    private NovelMapper novelMapper;
    /**
     * 小说章节数据映射
     */
    @Autowired
    private NovelChapterMapper novelChapterMapper;
    /**
     * 小说文档解析器
     */
    @Autowired
    private NovelDocumentAnalyzer novelDocumentAnalyzer;
    /**
     * Mybatis工具类
     */
    @Autowired
    private MybatisUtils mybatisUtils;

    /**
     * 抓取小说列表
     *
     * @param context   上下文
     * @param novelCode 小说编号
     * @return 结果
     */
    @Override
    public Integer spiderNovelList(Context context, String novelCode) {

        /*--------------------------------日志记录------------------------------------*/
        logger.debug("抓取小说列表，小说编号：{}", novelCode);

        /*--------------------------------参数校验------------------------------------*/
        Assert.notNull(novelCode, "小说编号不能为空！");
        UserVo userVo = context.getUser();
        Integer results = 0;
        Date nowDate = new Date();

        /*--------------------------------业务处理------------------------------------*/
        // 获取小说
        Novel novel = novelMapper.selectByNovelCode(novelCode);
        Assert.notNull(novel, "未查询到相关小说记录！");
        try {
            List<NovelChapter> novelChapterList = JsoupUtils.getDocumentList(novel.getUrl(), novelDocumentAnalyzer, NovelChapter.class);
            SnowFlake snowFlake = new SnowFlake(3, 1);
            for (int i = 0; i < novelChapterList.size(); i++) {
                NovelChapter novelChapter = novelChapterList.get(i);
                // 获取章节地址
                String url = novel.getUrl() + "/" + novelChapter.getUrl();
                novelChapter.setUrl(url);
                try {
                    // 获取章节内容
                    Map<String, Object> contentMap = JsoupUtils.getDocumentMap(url, novelDocumentAnalyzer);
                    novelChapter.setContent(contentMap.get("content") + "");
                    Thread.sleep(500);
                } catch (Exception e) {
                    novelChapter.setContent("");
                    logger.error("解析html章节详情异常，错误：{}", e.getMessage());
                }
                String chapterCode = "ZJ" + snowFlake.nextId();
                novelChapter.setChapterCode(chapterCode);
                novelChapter.setNovelCode(novelCode);
                novelChapter.setDisplayOrder(i);
                novelChapter.setFlagDelete(CommonConst.FLAG_DELETE_NO);
                novelChapter.setCreator(userVo.getUsername());
                novelChapter.setCreatorIp(context.getRequest().getRemoteAddr());
                novelChapter.setCreateTime(nowDate);
                novelChapter.setModifier(userVo.getUsername());
                novelChapter.setModifierIp(context.getRequest().getRemoteAddr());
                novelChapter.setModifyTime(nowDate);
                // 获取比例
                int percent = (i + 1) * 100 / novelChapterList.size();
                // websocket发送实时转换进度 todo
                logger.info("已加载" + percent + "%");
            }
            // 数据存入数据库
            results = mybatisUtils.batchInsert(novelChapterList, "com.xin.daily.dao.novel.NovelChapterMapper.insert", 1000);
        } catch (Exception e) {
            logger.error("解析html章节列表异常，错误：{}", e.getMessage());
        }

        /*--------------------------------日志记录------------------------------------*/
        logger.debug("抓取小说列表结束，插入记录数：{}", results);

        /*--------------------------------方法返回------------------------------------*/
        return results;
    }

    /**
     * 完善小说内容
     *
     * @param context   上下文
     * @param novelCode 小说编号
     * @return 结果
     */
    @Override
    public List<NovelChapterVo> improveNovel(Context context, String novelCode) {

        /*--------------------------------日志记录------------------------------------*/
        logger.debug("完善小说内容，小说编号：{}", novelCode);

        /*--------------------------------参数校验------------------------------------*/
        Assert.notNull(novelCode, "小说编号不能为空！");
        boolean result = true;

        /*--------------------------------业务处理------------------------------------*/
        // 查询小说章节内容为空的记录
        List<NovelChapterVo> novelChapterVoList = novelChapterMapper.selectListForEmptyByNovelCode(novelCode);
        if (novelChapterVoList.size() > 0) {
            // 循环抓取内容
            for (NovelChapterVo novelChapterVo : novelChapterVoList) {
                try {
                    Map<String, Object> map = JsoupUtils.getDocumentMap(novelChapterVo.getUrl(), novelDocumentAnalyzer);
                    novelChapterMapper.updateContentByChapterCode(novelChapterVo.getChapterCode(), map.get("content") + "");
                } catch (Exception e) {
                    logger.error("解析html章节详情异常，错误：{}", e.getMessage());
                }
            }
        }

        /*--------------------------------日志记录------------------------------------*/
        logger.debug("完善小说内容，更新记录数：{}", novelChapterVoList.size());

        /*--------------------------------方法返回------------------------------------*/
        return novelChapterVoList;
    }

    /**
     * 获取小说更新章节
     *
     * @param context   上下文
     * @param novelCode 小说编号
     * @return 结果
     */
    @Override
    public List<NovelChapter> updateNovel(Context context, String novelCode) {

        /*--------------------------------日志记录------------------------------------*/
        logger.debug("获取小说更新章节");

        /*--------------------------------参数校验------------------------------------*/
        UserVo userVo = context.getUser();
        Date nowDate = new Date();
        // 存放插入列表
        List<NovelChapter> novelChapters = new ArrayList<>();

        /*--------------------------------业务处理------------------------------------*/
        // 获取最新章节
        NovelChapterVo novelChapterVo = novelChapterMapper.selectMaxByNovelCode(novelCode);
        Assert.notNull(novelChapterVo, "未查询到相关章节！");
        int display_order = novelChapterVo.getDisplayOrder();
        // 抓取章节列表
        try {
            List<NovelChapter> novelChapterList = JsoupUtils.getDocumentList(novelChapterVo.getNovelUrl(), novelDocumentAnalyzer, NovelChapter.class);
            SnowFlake snowFlake = new SnowFlake(3, 2);
            for (int i = novelChapterList.size() - 1; i >= 0; i++) {
                // 倒序更新
                NovelChapter novelChapter = novelChapterList.get(i);
                if (novelChapter.getChapterName().equals(novelChapterVo.getChapterName())) {
                    // 更新结束
                    break;
                }
                // 生成实体
                String chapterCode = "ZJ" + snowFlake.nextId();
                novelChapter.setChapterCode(chapterCode);
                novelChapter.setNovelCode(novelCode);
                novelChapter.setDisplayOrder(++display_order);
                novelChapter.setFlagDelete(CommonConst.FLAG_DELETE_NO);
                novelChapter.setCreator(userVo.getUsername());
                novelChapter.setCreatorIp(context.getRequest().getRemoteAddr());
                novelChapter.setCreateTime(nowDate);
                novelChapter.setModifier(userVo.getUsername());
                novelChapter.setModifierIp(context.getRequest().getRemoteAddr());
                novelChapter.setModifyTime(nowDate);
                novelChapters.add(novelChapter);
            }
            // 倒序列表
            Collections.reverse(novelChapters);
            // 插入记录
            novelChapterMapper.batchInsert(novelChapters);
        } catch (Exception e) {
            logger.error("获取章节列表失败！，错误：{}", e);
        }

        /*--------------------------------日志记录------------------------------------*/
        logger.debug("获取小说更新章节，更新数量：{}", novelChapters.size());

        /*--------------------------------方法返回------------------------------------*/
        return novelChapters;
    }
}
