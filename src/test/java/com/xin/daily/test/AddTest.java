package com.xin.daily.test;

import com.xin.web.utils.convert.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author creator mafh 2019/12/3 9:43
 * @author updater
 * @version 1.0.0
 */
public class AddTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        List<Integer> subList = list.subList(0,7);
        System.out.println(JsonUtils.toJson(subList));
    }
}
