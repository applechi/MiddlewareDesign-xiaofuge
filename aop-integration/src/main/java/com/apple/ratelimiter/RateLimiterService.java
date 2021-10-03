package com.apple.ratelimiter;

import com.apple.annotation.DoIntegration;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

public interface RateLimiterService {

    Object access(ProceedingJoinPoint jp, Method method, DoIntegration doRateLimiter, Object[] args) throws Throwable;

}