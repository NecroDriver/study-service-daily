package com.xin.daily.common.mvc;

import com.xin.redis.util.RedisUtils;
import com.xin.web.consts.CookieConst;
import com.xin.web.consts.RedisConst;
import com.xin.web.pojo.Context;
import com.xin.web.utils.convert.JsonUtils;
import com.xin.web.utils.cookie.CookieUtils;
import com.xin.web.vo.UserVo;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ContextArgumentResolver 请求参数注入
 * 自定义解析器
 *
 * @author lemon 2019/11/30 20:57
 * @version V1.0.0
 **/
@Component
public class ContextArgumentResolver extends HandlerMethodArgumentResolverComposite {

    private RedisUtils redisUtils;

    public ContextArgumentResolver(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    /**
     * 用来验证是否适合解析某种类型的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 有context参数则进行注入
        return parameter.getParameterType().isAssignableFrom(Context.class);
    }

    /**
     * 负责解析参数
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Context context = new Context();
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        context.setRequest(request);
        context.setResponse(webRequest.getNativeResponse(HttpServletResponse.class));
        // 从cookie获取token
        String token = CookieUtils.getCookieValue(request, CookieConst.USER_TOKEN);
        if (!StringUtils.isEmpty(token)) {
            // 从redis中获取用户信息
            Object userJson = redisUtils.get(RedisConst.USER_LOGIN_KEY + token);
            if (!ObjectUtils.isEmpty(userJson)) {
                // 登录信息赋值
                UserVo userVo = JsonUtils.fromJson(userJson.toString(), UserVo.class);
                context.setUser(userVo);
            }
        }
        return context;
    }
}
