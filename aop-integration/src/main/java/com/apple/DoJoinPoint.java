package com.apple;

import com.apple.annotation.DoIntegration;
import com.apple.valve.IValveService;
import com.apple.valve.impl.HystrixValveImpl;
import com.apple.whitelist.IWhiteListService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@Aspect
@Component
public class DoJoinPoint {

    private Logger logger = LoggerFactory.getLogger(DoJoinPoint.class);
    
    @Autowired
    private IValveService iValveService;

    @Autowired
    private IWhiteListService iWhiteListService;

    @Pointcut("@annotation(com.apple.annotation.DoIntegration)")
    public void aopPoint() {
    }

    @Around("aopPoint()&& @annotation(doIntegration)")
    public Object doRouter(ProceedingJoinPoint jp, DoIntegration doIntegration) throws Throwable {
        // 获取内容
        Method method = ReflectUtils.getMethod(jp);
        //DoIntegration doIntegration = method.getAnnotation(DoIntegration.class);

        //执行熔断处理
        if (doIntegration.timeoutValue() != 0) {
            //注意这里好像要每次请求都要生成一个实例，不然会报错，采用spring 多例@Scope("prototype")只是多个地方@autoWire是多个实例，但是对于同一个controller注入还是单例的！！！
            return new HystrixValveImpl().access(jp, method, doIntegration, jp.getArgs());
            //return iValveService.access(jp, method, doIntegration, jp.getArgs());
        }
        //执行白名单处理
        if (!"".equals(doIntegration.key())) {
            return iWhiteListService.access(doIntegration, jp);
        }
        
        return null;
    }
}
