package com.xin.daily.service.novel;

import com.xin.daily.entity.novel.NovelChapter;
import com.xin.daily.vo.NovelChapterVo;
import com.xin.web.pojo.Context;

import java.util.List;

/**
 * 小说抓取service
 *
 * @author creator mafh 2019/12/2 21:16
 * @author updater
 * @version 1.0.0
 */
public interface INovelSpiderService {

    /**
     * 抓取小说列表
     *
     * @param context   上下文
     * @param novelCode 小说编号
     * @return 结果
     */
    Integer spiderNovelList(Context context, String novelCode);

    /**
     * 完善小说内容
     *
     * @param context   上下文
     * @param novelCode 小说编号
     * @return 结果
     */
    List<NovelChapterVo> improveNovel(Context context, String novelCode);

    /**
     * 获取小说更新章节
     *
     * @param context   上下文
     * @param novelCode 小说编号
     * @return 结果
     */
    List<NovelChapter> updateNovel(Context context, String novelCode);
}
