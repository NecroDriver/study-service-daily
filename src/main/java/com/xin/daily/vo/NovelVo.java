package com.xin.daily.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 小说实体
 *
 * @author creator mafh 2019/11/28 17:56
 * @author updater
 * @version 1.0.0
 */
@Data
public class NovelVo implements Serializable {

    /**
     * 小说编号
     */
    private String novelCode;

    /**
     * 小说名称
     */
    private String novelName;

    /**
     * 简介
     */
    private String description;

    /**
     * 小说地址
     */
    private String url;

    /**
     * 封面路径
     */
    private String coverImg;

    /**
     * 是否删除，0：否 1：是
     */
    private Byte flagDelete;

    /**
     * 是否更新,0:否 1:是
     */
    private Byte flagUpdate;

    /**
     * 是否完结,0:否 1:是
     */
    private Byte flagEnd;
}