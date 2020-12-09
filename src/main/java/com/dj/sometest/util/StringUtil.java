package com.dj.sometest.util;

import java.text.NumberFormat;

/**
 * @Author: Chris
 * @Date: 2020/11/23 18:15
 */
public class StringUtil {

    public static String geFourNumber(int number,int length){
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(length);
        formatter.setGroupingUsed(false);
        return formatter.format(number);
    }

    public static String length(int number){
        StringBuilder s = new StringBuilder();
       for(int i =0;i<number;i++){
           s.append("9");
       }
       return s.toString();
    }

    public static String getNumber(String s,int length){
        //999 - 998 = 1
        Integer number = Integer.valueOf(length(length));
        Integer value = Integer.valueOf(s);
        int i = number - value;
        String s1 = geFourNumber(i, length);
        return s1;
    }
}
