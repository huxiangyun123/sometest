package com.dj.sometest.paixu;

import java.util.Arrays;

/**
 * @Author: Chris
 * @Date: 2020/12/8 21:10
 */
public class SelectorSort {

    public static void main(String[] args) {

        int arr[] = {8, 4, 3, 9, 5, 6, 1};


        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = 1 + i; j < arr.length; j++) {
                if (min > arr[j]) {
                    //说明假定的min并不是最小值
                    //则替换
                    min = arr[j];
                    minIndex = j;
                }
            }
            //第一次将最小值放到最前面
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }

        System.out.println(Arrays.toString(arr));

        int minIndex = 0;
        int min = arr[0];
        for (int j = 1; j < arr.length; j++) {
            if (min > arr[j]) {
                //说明假定的min并不是最小值
                //则替换
                min = arr[j];
                minIndex = j;
            }
        }
        //第一次将最小值放到最前面
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
        }
    }
}
