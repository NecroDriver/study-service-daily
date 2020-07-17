package com.xin.daily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * 日常服务应用
 *
 * @author lemon
 */
@SpringBootApplication(scanBasePackages = "com.xin")
@EnableOpenApi
@EnableDiscoveryClient
public class StudyServiceDailyApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyServiceDailyApplication.class, args);
    }

}
