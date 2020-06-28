package com.xin.daily.controller.config;

import com.xin.daily.common.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConfigController nacos配置中心样例
 *
 * @author lemon 2020/6/9 15:32
 * @version 1.0.0
 **/
@RestController
@RequestMapping("/public/study/config")
public class ConfigController {

    @Autowired
    private ServiceConfig serviceConfig;

    @GetMapping("/get")
    public int get() {
        return serviceConfig.getCustomNum();
    }
}
