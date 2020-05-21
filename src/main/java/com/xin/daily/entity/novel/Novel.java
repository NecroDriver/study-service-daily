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
@ApiModel(value = "com-xin-daily-entity-novel-Novel")
@Data
public class Novel implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Integer id;

    /**
     * 小说编号
     */
    @ApiModelProperty(value = "小说编号")
    private String novelCode;

    /**
     * 小说名称
     */
    @ApiModelProperty(value = "小说名称")
    private String novelName;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String description;

    /**
     * 小说地址
     */
    @ApiModelProperty(value = "小说地址")
    private String url;

    /**
     * 封面路径
     */
    @ApiModelProperty(value = "封面路径")
    private String coverImg;

    /**
     * 是否删除，0：否 1：是
     */
    @ApiModelProperty(value = "是否删除，0：否 1：是")
    private Byte flagDelete;

    /**
     * 是否更新,0:否 1:是
     */
    @ApiModelProperty(value = "是否更新,0:否 1:是")
    private Byte flagUpdate;

    /**
     * 是否完结,0:否 1:是
     */
    @ApiModelProperty(value = "是否完结,0:否 1:是")
    private Byte flagEnd;

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

    private static final long serialVersionUID = 1L;
}