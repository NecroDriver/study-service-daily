package com.xin.daily.common.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * ServiceConfig 业务配置项
 *
 * @author mfh 2020/6/28 16:18
 * @version 1.0.0
 **/
@Component
@Getter
@RefreshScope
public class ServiceConfig {

    @Value("${service.customNum:1}")
    private int customNum;
}
