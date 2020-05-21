package com.xin.daily.controller.novel;

import com.github.pagehelper.PageInfo;
import com.xin.daily.service.novel.INovelService;
import com.xin.daily.vo.NovelChapterVo;
import com.xin.daily.vo.NovelVo;
import com.xin.web.base.BaseController;
import com.xin.web.pojo.Context;
import com.xin.web.pojo.Pageable;
import com.xin.web.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小说controller
 *
 * @author creator mafh 2019/11/28 14:36
 * @author updater
 * @version 1.0.0
 */
@RestController
@RequestMapping("/study/novel")
public class NovelController extends BaseController {

    /**
     * 小说service
     */
    @Autowired
    private INovelService novelService;

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
    @PostMapping("/save")
    public ResultVo saveNovelInfo(Context context, String novelName, String url, String coverImg, String description, Byte flagEnd) {
        int num = novelService.saveNovelInfo(context, novelName, url, coverImg, description, flagEnd);
        return ResultVo.newResultVo(num > 0, num, "小说保存");
    }

    /**
     * 获取小说分页
     *
     * @param context   上下文
     * @param orderType 排序类型
     * @param pageable  分页对象
     * @return 分页数据
     */
    @PostMapping("/public/getNovelPage")
    public ResultVo getNovelPage(Context context, Integer orderType, Pageable pageable) {
        PageInfo<NovelVo> novelPage = novelService.getNovelPage(context, orderType, pageable);
        return ResultVo.newResultVo(true, novelPage, "获取小说分页");
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
    @PostMapping("/public/getNovelChapterPage")
    public ResultVo getNovelChapterPage(Context context, String novelCode, String keyword, Integer orderType, Pageable pageable) {
        PageInfo<NovelChapterVo> novelChapterPage = novelService.getNovelChapterPage(context, novelCode, keyword, orderType, pageable);
        return ResultVo.newResultVo(true, novelChapterPage, "获取小说章节分页");
    }

    /**
     * 根据章节编号获取章节详情
     *
     * @param context     上下文
     * @param chapterCode 章节编号
     * @return 详情
     */
    @PostMapping("/public/getNovelChapterInfo")
    public ResultVo getNovelChapterInfo(Context context, String chapterCode) {
        NovelChapterVo novelChapterVo = novelService.getNovelChapterInfo(context, chapterCode);
        return ResultVo.successVo(novelChapterVo);
    }
}
