package com.xin.daily.common.mvc;

import com.xin.redis.util.RedisUtils;
import com.xin.web.consts.CookieConst;
import com.xin.web.consts.RedisConst;
import com.xin.web.interceptor.BaseHandlerInterceptorAdapter;
import com.xin.web.pojo.UrlRule;
import com.xin.web.utils.convert.JsonUtils;
import com.xin.web.utils.cookie.CookieUtils;
import com.xin.web.utils.filter.FilterUtils;
import com.xin.web.utils.filter.IUrlFilter;
import com.xin.web.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MVC拦截器
 *
 * @author creator mafh 2019/11/27 16:55
 * @author updater
 * @version 1.0.0
 */
@Component
public class MvcInterceptor extends BaseHandlerInterceptorAdapter implements IUrlFilter {

    private Logger logger = LoggerFactory.getLogger(MvcInterceptor.class);

    /**
     * 应用上下文
     */
    private ApplicationContext applicationContext;
    /**
     * redis工具类
     */
    private RedisUtils redisUtils;

    public MvcInterceptor(ApplicationContext applicationContext, RedisUtils redisUtils) {
        this.applicationContext = applicationContext;
        this.redisUtils = redisUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证url
        if (FilterUtils.filterUrl(applicationContext, request)) {
            return true;
        }
        // 从cookie获取token
        String token = CookieUtils.getCookieValue(request, CookieConst.USER_TOKEN);
        if (!StringUtils.isEmpty(token)) {
            // 从redis中获取用户信息
            Object userJson = redisUtils.get(RedisConst.USER_LOGIN_KEY + token);
            if (!ObjectUtils.isEmpty(userJson)) {
                // 登录信息赋值
                return true;
            }
        }
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            out = response.getWriter();
            Map<String, Object> data = new HashMap<>();
            data.put("loginUrl", "");
            ResultVo result = ResultVo.failureVo("未授权，被拦截", data);
            out.print(JsonUtils.toJson(result));
            out.flush();
            logger.info("{}请求未授权，被拦截", request.getRequestURL().toString());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return false;
    }

    /**
     * This implementation is empty.
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * This implementation is empty.
     *
     * @param request
     * @param response
     * @param handler
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    /**
     * 排除的规则
     */
    @Override
    public List<String> getExcludePathPatterns() {
        List<String> list = super.getExcludePathPatterns();
        list.add("/error");
        return list;
    }

    /**
     * 过滤的规则
     *
     * @return 列表
     */
    @Override
    public List<UrlRule> getUrlRuleList() {
        List<UrlRule> list = new ArrayList<>();
        list.add(new UrlRule("/public", UrlRule.Rule.contains));
        return list;
    }
}
