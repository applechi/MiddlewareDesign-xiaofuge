package com.apple;

import com.alibaba.fastjson.JSON;
import com.apple.annotation.DoIntegration;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author qiping.chi
 * @date 2021-09-20 15:15
 **/
public class ReflectUtils {

    public static Object returnObject(DoIntegration doIntegration, Method method) {
        //获取调用方法的返回类型，这里是controller调用所以就是UserInfo
        Class<?> returnType = method.getReturnType();
        String returnJson = doIntegration.returnJson();
        if ("".equals(returnJson)) {
            try {
                return returnType.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JSON.parseObject(returnJson, returnType);
    }

    public static Object returnDefaultObject(Method method) {
        return JSON.parseObject("{\"code\":\"1111\",\"info\":\"Hello,默认返回！\"}", method.getReturnType());
    }

    // 获取属性值
    public static String getFiledValue(String filed, Object[] args) {
        String filedValue = null;
        for (Object arg : args) {
            try {
                if (null == filedValue || "".equals(filedValue)) {
                    filedValue = BeanUtils.getProperty(arg, filed);
                } else {
                    break;
                }
            } catch (Exception e) {
                if (args.length == 1) {
                    return args[0].toString();
                }
            }
        }
        return filedValue;
    }

    public static Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

 
    public static Class<? extends Object> getClass(JoinPoint jp) throws NoSuchMethodException {
        return jp.getTarget().getClass();
    }
}
