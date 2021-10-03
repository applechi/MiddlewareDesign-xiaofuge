# 服务治理，统一白名单控制

## 一、前言

1. 暂时可以把个人提交的代码，合并到dev分支
2. 也支持个人fork到本地，再修改后提交pr
3. 一些想法可以提交issues

## 二、Q&A

- [X] 关于 **DoJoinPoint** 切面的两种使用方式：[issues](https://codechina.csdn.net/MiddlewareDesign/whitelist-spring-boot-starter/-/issues/1)

1. 定义 EnableXxxx 注解，配置扫描范围。

    ```java
    @ComponentScan("cn.bugstack.middleware.*")
    public @interface EnableDcsScheduling {
    }
    ```

2. 在 WhiteListAutoConfigure 中配置 Bean，去掉 DoJoinPoint 中的 @Component

    ```java
    @Bean
    @ConditionalOnMissingBean
    public DoJoinPoint point(){
        return new DoJoinPoint();
    }
    ```
