package com.xin.daily.controller.user;

import com.xin.daily.service.user.IUserLoginService;
import com.xin.web.base.BaseController;
import com.xin.web.pojo.Context;
import com.xin.web.vo.ResultVo;
import com.xin.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录控制类
 *
 * @author creator mafh 2019/11/15 13:40
 * @author updater
 * @version 1.0.0
 */
@RestController
@RequestMapping("/study/user")
public class UserLoginController extends BaseController {

    /**
     * 用户登录service
     */
    @Autowired
    private IUserLoginService userLoginService;

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
    @PostMapping(value = "/public/register")
    public ResultVo register(Context context, String account, String username, String password, String phone, String email) {
        int num = userLoginService.register(context, account, username, password, phone, email);
        return ResultVo.newResultVo(num > 0, num, "注册");
    }

    /**
     * 登录
     *
     * @param context  上下文
     * @param account  账户
     * @param password 密码
     * @return 结果
     */
    @PostMapping("/public/login")
    public ResultVo login(Context context, String account, String password) {
        UserVo userVo = userLoginService.login(context, account, password);
        return ResultVo.successVo(userVo);
    }

    /**
     * 获取当前登录信息
     *
     * @param context 上下文
     * @return 用户信息
     */
    @PostMapping("/login/info")
    public ResultVo getUserInfo(Context context) {
        UserVo userVo = userLoginService.getUserInfo(context);
        return ResultVo.successVo(userVo);
    }

    /**
     * 登出
     *
     * @param context 上下文
     * @return 结果
     */
    @PostMapping("/login/out")
    public ResultVo loginOut(Context context) {
        boolean result = userLoginService.loginOut(context);
        return ResultVo.newResultVo(result, "登出");
    }
}
