package com.xin.daily.entity.novel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author creator mafh 2019/11/28 17:56
 * @author updater
 * @version 1.0.0
 */
@ApiModel(value = "com-xin-daily-entity-novel-NovelChapter")
@Data
public class NovelChapter implements Serializable {
    @ApiModelProperty(value = "null")
    private Integer id;

    /**
     * 小说编号
     */
    @ApiModelProperty(value = "小说编号")
    private String novelCode;

    /**
     * 章节编号
     */
    @ApiModelProperty(value = "章节编号")
    private String chapterCode;

    /**
     * 章节名称
     */
    @ApiModelProperty(value = "章节名称")
    private String chapterName;

    /**
     * 章节地址
     */
    @ApiModelProperty(value = "章节地址")
    private String url;

    /**
     * 显示顺序
     */
    @ApiModelProperty(value = "显示顺序")
    private Integer displayOrder;

    /**
     * 是否删除 0否1是
     */
    @ApiModelProperty(value = "是否删除 0否1是")
    private Byte flagDelete;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String creator;

    /**
     * 创建人ip
     */
    @ApiModelProperty(value = "创建人ip")
    private String creatorIp;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String modifier;

    /**
     * 修改人ip
     */
    @ApiModelProperty(value = "修改人ip")
    private String modifierIp;

    /**
     * 章节内容
     */
    @ApiModelProperty(value = "章节内容")
    private String content;

    private static final long serialVersionUID = 1L;
}