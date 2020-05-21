package com.xin.daily.entity.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author creator mafh 2019/11/28 17:59
 * @author updater
 * @version 1.0.0
 */
@ApiModel(value = "com-xin-daily-entity-user-UserLogin")
@Data
public class UserLogin implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Integer id;

    /**
     * 登录账户
     */
    @ApiModelProperty(value = "登录账户")
    private String account;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private String userCode;

    /**
     * 加密密码
     */
    @ApiModelProperty(value = "加密密码")
    private String encryptPassword;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 平台
     */
    @ApiModelProperty(value = "平台")
    private String platform;

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

    private static final long serialVersionUID = 1L;
}