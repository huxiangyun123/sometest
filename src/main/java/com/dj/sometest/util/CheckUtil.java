package com.dj.sometest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Chris
 * @Date: 2020/12/1 14:57
 */
public class CheckUtil {

    public static Pattern p  = Pattern.compile("^[a-zA-Z0-9-]{0,50}$");

    public static Pattern p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的

    public static Pattern p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");     // 验证没有区号的


    public static boolean isPhone(String str) {
        Matcher m = null;
        boolean isPhone = false;

        if (str.length() > 9) {
            m = p1.matcher(str);
            isPhone = m.matches();
        } else {
            m = p2.matcher(str);
            isPhone = m.matches();
        }
        return isPhone;
    }

    public static boolean isPhone2(String code) {
        Matcher matcher = p.matcher(code);
        return matcher.matches();
    }


    public static void main(String[] args) {

        String phone = "0571-2329070";
        if (isPhone2(phone)) {
            System.out.println(phone + "是符合的电话号码");
        } else {
            System.out.println(phone + "不符合");
        }
    }
}
