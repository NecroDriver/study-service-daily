package com.xin.daily.study.sort;

import java.util.Arrays;

/**
 * Sort
 *
 * @author mfh 2020/9/24 18:04
 * @version 1.0.0
 **/
public class Sort {

    private static final int[] ARR = {3, 9, -1, 10, 20, 16};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(ARR));
        bubble(ARR);
        System.out.println(Arrays.toString(ARR));
    }

    /**
     * 冒泡排序
     *
     * @param arr 数组
     */
    public static void bubble(int[] arr) {
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换顺序
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
