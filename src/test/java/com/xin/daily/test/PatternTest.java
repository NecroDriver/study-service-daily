package com.xin.daily.test;

import com.xin.web.utils.convert.JsonUtils;

/**
 * @author creator mafh 2019/12/27 13:18
 * @author updater
 * @version 1.0.0
 */
public class PatternTest {

    public static void main(String[] args) {
        String url = "/branchManage/res/toMediaCheckList.html";
        String[] arr1= url.split("/");
        System.out.println(JsonUtils.toJson(arr1));
    }
}
