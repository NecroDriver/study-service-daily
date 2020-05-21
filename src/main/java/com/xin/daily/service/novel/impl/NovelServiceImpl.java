package com.xin.daily.service.novel.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.daily.common.consts.CommonConst;
import com.xin.daily.dao.novel.NovelChapterMapper;
import com.xin.daily.dao.novel.NovelMapper;
import com.xin.daily.entity.novel.Novel;
import com.xin.daily.service.novel.INovelService;
import com.xin.daily.vo.NovelChapterVo;
import com.xin.daily.vo.NovelVo;
import com.xin.web.base.BaseService;
import com.xin.web.pojo.Context;
import com.xin.web.pojo.Pageable;
import com.xin.web.utils.crypt.SnowFlake;
import com.xin.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * 小说service实现类
 *
 * @author creator mafh 2019/11/28 16:12
 * @author updater
 * @version 1.0.0
 */
@Service
@Transactional
public class NovelServiceImpl extends BaseService implements INovelService {

    /**
     * 小说dao
     */
    @Autowired
    private NovelMapper novelMapper;
    /**
     * 小说章节dao
     */
    @Autowired
    private NovelChapterMapper novelChapterMapper;

    /**
     * 保存小说
     *
     * @param context     上下文
     * @param novelName   小说名称
     * @param url         小说地址
     * @param coverImg    封面图片
     * @param description 描述
     * @param flagEnd     是否完结
     * @return 结果
     */
    @Override
    public int saveNovelInfo(Context context, String novelName, String url, String coverImg, String description, Byte flagEnd) {

        /*-----------------------------日志记录-----------------------------*/
        logger.debug("保存小说，小说名称：{}，小说地址：{}，封面图片：{}，描述：{}，是否完结：{}", novelName, url, coverImg, description, flagEnd);

        /*-----------------------------参数处理-----------------------------*/
        Assert.notNull(novelName, "小说名称不能为空！");
        Assert.notNull(url, "小说名称不能为空！");
        Assert.notNull(coverImg, "小说地址不能为空！");
        Assert.notNull(description, "封面图片不能为空！");
        Assert.notNull(flagEnd, "是否完结不能为空！");
        Date nowDate = new Date();
        UserVo userVo = context.getUser();

        /*-----------------------------业务处理-----------------------------*/
        // 判别小说名称是否重复
        Assert.isNull(novelMapper.selectByNovelName(novelName), "小说名称已存在！");
        Novel novel = new Novel();
        novel.setNovelName(novelName);
        SnowFlake snowFlake = new SnowFlake(2, 1);
        String novelCode = "XS" + snowFlake.nextId();
        novel.setNovelCode(novelCode);
        novel.setUrl(url);
        novel.setCoverImg(coverImg);
        novel.setDescription(description);
        novel.setFlagEnd(flagEnd);
        novel.setFlagUpdate(CommonConst.FLAG_UPDATE_NO);
        novel.setFlagDelete(CommonConst.FLAG_DELETE_NO);
        novel.setCreator(userVo.getUsername());
        novel.setCreateTime(nowDate);
        novel.setCreatorIp(context.getRequest().getRemoteAddr());
        novel.setModifier(userVo.getUsername());
        novel.setModifyTime(nowDate);
        novel.setModifierIp(context.getRequest().getRemoteAddr());
        int num = novelMapper.insert(novel);

        /*-----------------------------日志记录-----------------------------*/
        logger.debug("保存小说结束，小说id：{}", novel.getId());

        /*-----------------------------方法返回-----------------------------*/
        return num;
    }

    /**
     * 获取小说分页
     *
     * @param context   上下文
     * @param orderType 排序类型
     * @param pageable  分页对象
     * @return 分页数据
     */
    @Override
    public PageInfo<NovelVo> getNovelPage(Context context, Integer orderType, Pageable pageable) {

        /*--------------------------------日志记录------------------------------------*/
        logger.debug("获取小说分页，排序类型：{}", orderType);

        /*--------------------------------参数校验------------------------------------*/
        Assert.notNull(orderType, "排序类型不能为空！");
        String orderTypeStr = orderType.equals(0) ? "asc" : "desc";

        /*--------------------------------业务处理------------------------------------*/
        PageHelper.startPage(pageable.getPageNo(), pageable.getPageSize());
        List<NovelVo> novelVoList = novelMapper.selectListByOrderType(orderTypeStr);
        PageInfo<NovelVo> pageInfo = new PageInfo<>(novelVoList);

        /*--------------------------------日志记录------------------------------------*/
        logger.debug("获取小说分页，记录数：{}", pageInfo.getTotal());

        /*--------------------------------方法返回------------------------------------*/
        return pageInfo;
    }

    /**
     * 获取小说章节分页
     *
     * @param context   上下文
     * @param novelCode 小说编号
     * @param keyword   关键字
     * @param orderType 排序类型
     * @param pageable  分页对象
     * @return 分页数据
     */
    @Override
    public PageInfo<NovelChapterVo> getNovelChapterPage(Context context, String novelCode, String keyword, Integer orderType, Pageable pageable) {

        /*--------------------------------日志记录------------------------------------*/
        logger.debug("获取小说章节分页，小说编号：{}，关键字：{}", novelCode, keyword);

        /*--------------------------------参数校验------------------------------------*/
        Assert.notNull(novelCode, "小说编号不能为空！");
        Assert.notNull(orderType, "排序类型不能为空！");
        String orderTypeStr = orderType.equals(0) ? "asc" : "desc";
        keyword = "%" + keyword + "%";

        /*--------------------------------业务处理------------------------------------*/
        PageHelper.startPage(pageable.getPageNo(), pageable.getPageSize());
        List<NovelChapterVo> novelChapterVoList = novelChapterMapper.selectPageByNovelCode(novelCode, keyword, orderTypeStr);
        PageInfo<NovelChapterVo> page = PageInfo.of(novelChapterVoList);

        /*--------------------------------日志记录------------------------------------*/
        logger.debug("获取小说章节分页，记录数：{}", page.getTotal());

        /*--------------------------------方法返回------------------------------------*/
        return page;
    }

    /**
     * 根据章节编号获取章节详情
     *
     * @param context     上下文
     * @param chapterCode 章节编号
     * @return 详情
     */
    @Override
    public NovelChapterVo getNovelChapterInfo(Context context, String chapterCode) {

        /*--------------------------------日志记录------------------------------------*/
        logger.debug("根据章节编号获取章节详情，章节编号：{}", chapterCode);

        /*--------------------------------参数校验------------------------------------*/
        Assert.notNull(chapterCode, "章节编号不能为空！");

        /*--------------------------------业务处理------------------------------------*/
        NovelChapterVo novelChapterVo = novelChapterMapper.selectByChapterCode(chapterCode);

        /*--------------------------------日志记录------------------------------------*/
        logger.debug("根据章节编号获取章节详情，结果：{}", novelChapterVo);

        /*--------------------------------方法返回------------------------------------*/
        return novelChapterVo;
    }
}
