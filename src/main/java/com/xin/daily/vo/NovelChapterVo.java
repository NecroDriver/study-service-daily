package com.xin.daily.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 小说章节
 *
 * @author creator mafh 2019/11/28 17:56
 * @author updater
 * @version 1.0.0
 */
@Data
public class NovelChapterVo implements Serializable {

    /**
     * 小说编号
     */
    private String novelCode;
    /**
     * 章节编号
     */
    private String chapterCode;
    /**
     * 章节名称
     */
    private String chapterName;
    /**
     * 章节地址
     */
    private String url;
    /**
     * 显示顺序
     */
    private Integer displayOrder;
    /**
     * 章节内容
     */
    private String content;
    /**
     * 小说地址
     */
    private String novelUrl;

    private static final long serialVersionUID = 1L;
}