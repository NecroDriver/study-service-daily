package com.xin.daily.common.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * mvc配置类 可管理多个拦截器
 *
 * @author creator mafh 2019/11/28 10:46
 * @author updater
 * @version 1.0.0
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    /**
     * 注入自定义拦截器
     */
    private final MvcInterceptor mvcInterceptor;
    /**
     * 自定义解析器
     */
    private ContextArgumentResolver contextArgumentResolver;

    @Autowired
    public MvcConfiguration(ContextArgumentResolver contextArgumentResolver, MvcInterceptor mvcInterceptor) {
        this.contextArgumentResolver = contextArgumentResolver;
        this.mvcInterceptor = mvcInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mvcInterceptor).addPathPatterns(mvcInterceptor.getPathPatterns()).excludePathPatterns(mvcInterceptor.getExcludePathPatterns());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(contextArgumentResolver);
    }

    /**
     * 解决跨域问题
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }
}
