package com.xin.daily.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RequestMapping("/study/config")
@RefreshScope
public class ConfigController {

    @Value("${customNum:0}")
    private int customNum;

    @GetMapping("/get")
    public int get(){
        return customNum;
    }
}
