package com.apple.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@SpringBootApplication
//@ComponentScan(basePackages = {"cn.bugstack.middleware.*"})  //这个扫描自己工程的controller包
@ComponentScan(basePackages = {"com.apple.**"})   //这个扫描引入的工程包，aop-integration
//@ComponentScan(basePackageClasses = DoJoinPoint.class)
public class ApiTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTestApplication.class, args);
    }

}
