package com.apple.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoIntegration {
    
    String key() default "";                //白名单key

    int timeoutValue() default 0;           // 超时熔断

    double permitsPerSecond() default 0D;   // 限流许可量

    String method() default "";             //方法拓展

    String returnJson() default "";         // 失败结果 JSON
    
    
}
