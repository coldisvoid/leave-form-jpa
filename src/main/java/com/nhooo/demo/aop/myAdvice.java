package com.nhooo.demo.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class myAdvice {
    @Pointcut(value = "execution()")
    public void myPointcut(){


    }
    @Around()
}
