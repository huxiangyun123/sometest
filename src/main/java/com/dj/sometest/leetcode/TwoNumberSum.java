package com.dj.sometest.leetcode;

/**
 * @Author: Chris
 * @Date: 2021/2/5 17:26
 * 数组内两数和等于目标值
 */
public class TwoNumberSum {

    public static void main(String[] args) {
        int[] nums = {10,2,-8,1};
        int[] result = solution(nums, 2);
        System.out.println(result);
    }


    public static int[] solution(int[] nums, int target) {

        int[] arr = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int one = nums[i];
            int another = target - one;
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[j] == another){
                    arr[0] = i;
                    arr[1] = j;
                    break;
                }
            }
        }
        return arr;
    }
}
