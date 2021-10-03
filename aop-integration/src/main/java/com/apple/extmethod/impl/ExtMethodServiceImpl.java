package com.apple.extmethod.impl;

import com.apple.ReflectUtils;
import com.apple.annotation.DoIntegration;
import com.apple.extmethod.ExtMethodService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * @author qiping.chi
 * @date 2021-10-03 18:24
 **/
@Service
public class ExtMethodServiceImpl implements ExtMethodService {

    @Override
    public Object access(DoIntegration doExtMethod, ProceedingJoinPoint jp) throws Throwable {

        // 获取内容
        Method method = ReflectUtils.getMethod(jp);
        DoIntegration doMethodExt = method.getAnnotation(DoIntegration.class);
        // 获取拦截方法
        String methodName = doMethodExt.method();
        // 功能处理      
        // 不能用上面的ReflectUtils.getMethod得到,要先获取getClass方法再获取--TODO：具体还没看实现怎么样的
        Method methodExt = ReflectUtils.getClass(jp).getMethod(methodName, method.getParameterTypes());
        Class<?> returnType = methodExt.getReturnType();

        // 判断方法返回类型
        if (!returnType.getName().equals("boolean")) {
            throw new RuntimeException("annotation @DoMethodExt set method：" + methodName + " returnType is not boolean");
        }
        // 拦截判断正常，继续
        boolean invoke = (boolean) methodExt.invoke(jp.getThis(), jp.getArgs());
        // 返回结果
        return invoke ? jp.proceed() : ReflectUtils.returnObject(doExtMethod, method);
    }
}
