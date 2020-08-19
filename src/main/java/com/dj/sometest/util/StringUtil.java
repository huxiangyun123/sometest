package com.dj.sometest.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Chris
 * @Date: 2020/5/11 18:33
 */
public class StringUtil {




    public static List<String> cut2(String s) {
        if (s == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        int pre = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '省' || chars[i] == '市' || chars[i] == '区'){
                String sub = s.substring(pre,i+1);
                list.add(sub);
                pre = i +1;
            }
        }
        return list;
    }














    public static List<String> cut(String s) {
        if (s == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        int province = 0;
        int city = 0;
        int country = 0;
        int time = 0;
        String provinceName = null;
        String cityName = null;
        String countryName = null;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '省') {
                province = i + 1;
                provinceName = s.substring(0, province);
                list.add(provinceName);
            }
            if (chars[i] == '市') {
                time++;
                if (time == 1) {
                    city = i + 1;
                    cityName = s.substring(province, city);
                    list.add(cityName);
                } else {
                    country = i + 1;
                    countryName = s.substring(city, country);
                    list.add(countryName);
                }
            }
            if (chars[i] == '区' || chars[i] == '县') {
                country = i + 1;
                if(time > 1){

                }
                countryName = s.substring(city, country);
                list.add(countryName);
            }
        }

        return list;
    }



}
