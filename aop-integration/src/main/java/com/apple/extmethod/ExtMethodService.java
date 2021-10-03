package com.apple.extmethod;

import com.apple.annotation.DoIntegration;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author qiping.chi
 * @date 2021-09-20 15:11
 **/
public interface ExtMethodService {

    Object access(DoIntegration doExtMethod, ProceedingJoinPoint jp) throws Throwable;
}
