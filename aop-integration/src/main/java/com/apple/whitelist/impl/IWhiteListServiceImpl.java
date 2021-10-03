package com.apple.whitelist.impl;

import com.apple.DoJoinPoint;
import com.apple.ReflectUtils;
import com.apple.annotation.DoIntegration;
import com.apple.whitelist.IWhiteListService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author qiping.chi
 * @date 2021-09-20 15:25
 **/
@Service
public class IWhiteListServiceImpl implements IWhiteListService {

    @Resource
    private String whiteListConfig;

    private Logger logger = LoggerFactory.getLogger(DoJoinPoint.class);


    @Override
    public Object access(DoIntegration doWhiteList, ProceedingJoinPoint jp) throws Throwable {
        Method method = ReflectUtils.getMethod(jp);
        String keyValue = ReflectUtils.getFiledValue(doWhiteList.key(), jp.getArgs());
        logger.info("middleware whitelist handler method：{} value：{}", method.getName(), keyValue);
        if (null == keyValue || "".equals(keyValue)) {
            return jp.proceed();
        }

        String[] split = whiteListConfig.split(",");

        // 白名单过滤
        for (String str : split) {
            if (keyValue.equals(str)) {
                return jp.proceed();
            }
        }
        // 拦截
        return ReflectUtils.returnObject(doWhiteList, method);    
    }
}
