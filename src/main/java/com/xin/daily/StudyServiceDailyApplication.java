package com.xin.daily;

import com.xin.swagger.configuration.EnableStudySwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 日常服务应用
 *
 * @author lemon
 */
@SpringBootApplication(scanBasePackages = "com.xin")
@EnableStudySwagger2
@EnableDiscoveryClient
public class StudyServiceDailyApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyServiceDailyApplication.class, args);
    }

}
