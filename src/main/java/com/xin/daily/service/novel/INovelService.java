package com.xin.daily.service.novel;

import com.github.pagehelper.PageInfo;
import com.xin.daily.vo.NovelChapterVo;
import com.xin.daily.vo.NovelVo;
import com.xin.web.pojo.Context;
import com.xin.web.pojo.Pageable;

/**
 * 小说service
 *
 * @author creator mafh 2019/11/28 16:12
 * @author updater
 * @version 1.0.0
 */
public interface INovelService {

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
    int saveNovelInfo(Context context, String novelName, String url, String coverImg, String description, Byte flagEnd);

    /**
     * 获取小说分页
     *
     * @param context   上下文
     * @param orderType 排序类型
     * @param pageable  分页对象
     * @return 分页数据
     */
    PageInfo<NovelVo> getNovelPage(Context context, Integer orderType, Pageable pageable);

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
    PageInfo<NovelChapterVo> getNovelChapterPage(Context context, String novelCode, String keyword, Integer orderType, Pageable pageable);

    /**
     * 根据章节编号获取章节详情
     *
     * @param context     上下文
     * @param chapterCode 章节编号
     * @return 详情
     */
    NovelChapterVo getNovelChapterInfo(Context context, String chapterCode);
}
