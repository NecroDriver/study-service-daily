package com.xin.daily.service.user;

import com.xin.web.pojo.Context;
import com.xin.web.vo.UserVo;

/**
 * 用户登录service
 *
 * @author creator mafh 2019/11/15 13:43
 * @author updater
 * @version 1.0.0
 */
public interface IUserLoginService {

    /**
     * 注册
     *
     * @param context  上下文
     * @param account  账户
     * @param username 用户名
     * @param password 密码
     * @param phone    手机号
     * @param email    邮箱
     * @return num
     */
    int register(Context context, String account, String username, String password, String phone, String email);

    /**
     * 登录
     *
     * @param context  上下文
     * @param account  账户
     * @param password 密码
     * @return 结果
     */
    UserVo login(Context context, String account, String password);

    /**
     * 获取当前登录信息
     *
     * @param context 上下文
     * @return 用户信息
     */
    UserVo getUserInfo(Context context);

    /**
     * 登出
     *
     * @param context 上下文
     * @return 结果
     */
    boolean loginOut(Context context);
}
