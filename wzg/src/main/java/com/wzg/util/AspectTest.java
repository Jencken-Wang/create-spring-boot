package com.wzg.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTest {


    @Pointcut("@annotation(com.wzg.util.ExecutionResult)")
    public void aspectNode(){
    }


    @After("aspectNode()")
    public void after(){
        System.out.println("后置通知");
    }
    @Before("aspectNode()")
    public void before(){
        System.out.println("前置通知");
    }
    @Around("aspectNode()")
    public void around(ProceedingJoinPoint joinPoint){
        try {
            System.out.println("环绕通知开始");
            joinPoint.proceed();
            System.out.println("环绕通知结束");
        } catch (Throwable e){
            System.out.println(e);
        }
    }
}
