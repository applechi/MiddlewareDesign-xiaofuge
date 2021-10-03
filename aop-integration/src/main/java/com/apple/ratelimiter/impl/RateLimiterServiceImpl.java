package com.apple.ratelimiter.impl;

import com.apple.ReflectUtils;
import com.apple.annotation.DoIntegration;
import com.apple.constant.Constants;
import com.apple.ratelimiter.RateLimiterService;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * @author qiping.chi
 * @date 2021-10-03 17:59
 **/
@Service
public class RateLimiterServiceImpl implements RateLimiterService {

    @Override
    public Object access(ProceedingJoinPoint jp, Method method, DoIntegration doRateLimiter, Object[] args) throws Throwable {
        // 判断是否开启
        if (0 == doRateLimiter.permitsPerSecond()) return jp.proceed();

        String clazzName = jp.getTarget().getClass().getName();
        String methodName = method.getName();

        String key = clazzName + "." + methodName;

        if (null == Constants.rateLimiterMap.get(key)) {
            Constants.rateLimiterMap.put(key, RateLimiter.create(doRateLimiter.permitsPerSecond()));
        }

        RateLimiter rateLimiter = Constants.rateLimiterMap.get(key);
        if (rateLimiter.tryAcquire()) {
            return jp.proceed();
        }

        return ReflectUtils.returnObject(doRateLimiter,method);
    }
}
