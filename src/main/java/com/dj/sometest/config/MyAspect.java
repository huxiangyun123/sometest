package com.dj.sometest.config;

import com.dj.sometest.annotation.MyAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: Chris
 * @Date: 2020/8/25 15:42
 */
@Component
@Aspect
public class MyAspect {

    /**
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern)
     *                 throws-pattern?)
     */
    @Pointcut("execution(public void com.dj.sometest.controller.AopController.testaop1())")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before01(){
        System.out.println("切面执行========");
    }

   /* @Around("pointCut()")
    public void before02(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("切面执行========");
        joinPoint.proceed();
    }*/

    @Around("@annotation(com.dj.sometest.annotation.MyAnnotation)")
    public void before03(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature sig = joinPoint.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        MyAnnotation annotation = currentMethod.getAnnotation(MyAnnotation.class);
        String value = annotation.value();

        Object[] args = joinPoint.getArgs();
        System.out.println(args[0]);
        System.out.println("切面执行========");
        joinPoint.proceed();
    }

}
