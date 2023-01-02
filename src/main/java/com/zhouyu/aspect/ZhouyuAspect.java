package com.zhouyu.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author gaozebin
 * @date 2023/1/2 16:57
 */
//@Aspect
//@Component
public class ZhouyuAspect {
    @Before("execution(public void com.zhouyu.service.UserService.test())")
    public void zhouyuBefore(JoinPoint joinPoint){
        System.out.println("zhouyuBefore");
    }
}
