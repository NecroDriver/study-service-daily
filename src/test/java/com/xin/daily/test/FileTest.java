package com.xin.daily.test;

import com.xin.web.utils.convert.JsonUtils;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

/**
 * @author creator mafh 2019/12/17 11:43
 * @author updater
 * @version 1.0.0
 */
public class FileTest {

    public static void main(String[] args) {
        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\mafh\\Desktop\\活动中心改动.txt"));
        System.out.println(JsonUtils.toJson(file));
    }
}
