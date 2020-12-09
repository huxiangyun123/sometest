package com.dj.sometest.paixu;

import java.util.Arrays;

/**
 * @Author: Chris
 * @Date: 2020/12/8 20:46
 */
public class MaoPaoSort {

    public static void main(String[] args) {
        int arr[] = {8,4,3,9,5,6,1};

        int temp = 0;
        boolean flag = false;

        for(int i = 0;i<arr.length - 1;i++){
            for(int j =0;j<arr.length - 1-i;j++){
                if(arr[j] > arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }

            //优化
            if(flag = true){
                //某一次循环排序位置没有动，后面则不用在循环
                break;
            }else {
                flag = false;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
