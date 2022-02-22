package com.nhooo.demo.aop;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
//自定义注解，用于标记需要使用aop的服务层方法
public @interface MyTransactional {
}
