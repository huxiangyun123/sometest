package com.dj.sometest.paixu;

import java.util.Arrays;

/**
 * @Author: Chris
 * @Date: 2020/12/8 21:34
 */
public class InsertSort {

    public static void main(String[] args) {
        int arr[] = {8, 4, 3, 9, 5, 6, 1};

        for(int i =0;i<arr.length -1;i++){
            int insertValue = arr[i+1];
            int insertIndex = i;

            while(insertIndex>=0 && insertValue < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            //退出while循环时，说明找到插入的位置
            arr[insertIndex + 1] = insertValue;
        }

        System.out.println(Arrays.toString(arr));
    }
}
