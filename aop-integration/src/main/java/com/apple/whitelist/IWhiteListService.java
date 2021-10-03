package com.apple.whitelist;

import com.apple.annotation.DoIntegration;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author qiping.chi
 * @date 2021-09-20 15:11
 **/
public interface IWhiteListService {

    Object access(DoIntegration doWhiteList, ProceedingJoinPoint jp) throws Throwable;
}
