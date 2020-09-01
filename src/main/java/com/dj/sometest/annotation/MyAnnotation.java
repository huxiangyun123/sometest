package com.dj.sometest.annotation;

/**
 * @Author: Chris
 * @Date: 2020/8/25 16:24
 */

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {

    String value();
}
