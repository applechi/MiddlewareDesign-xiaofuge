package com.apple.test.interfaces;

import com.apple.annotation.DoIntegration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 通过：http://localhost:8081/api/queryUserInfo?userId=aaa
     * 拦截：http://localhost:8081/api/queryUserInfo?userId=123
     */
    //@DoIntegration(key = "userId", returnJson = "{\"code\":\"1111\",\"info\":\"非白名单可访问用户拦截！\"}")
    //@DoIntegration(timeoutValue = 350, returnJson = "{\"code\":\"1111\",\"info\":\"调用方法超过350毫秒，熔断返回！\"}")
    //@DoIntegration(permitsPerSecond = 0, returnJson = "{\"code\":\"1111\",\"info\":\"调用方法超过最大次数，限流返回！\"}")
    @DoIntegration(method = "blacklist", returnJson = "{\"code\":\"1111\",\"info\":\"自定义校验方法拦截，不允许访问！\"}")
    @RequestMapping(path = "/api/queryUserInfo", method = RequestMethod.GET)
    public UserInfo queryUserInfo(@RequestParam String userId) throws InterruptedException {
        logger.info("查询用户信息，userId：{}", userId);
        //Thread.sleep(1000);
        //ReflectUtils.getFiledValue(null, null);
        return new UserInfo("虫虫:" + userId, 19, "天津市东丽区万科赏溪苑14-0000");
    }

    /**
     * 自定义黑名单，拦截方法
     */
    public boolean blacklist(@RequestParam String userId) {
        if ("bbb".equals(userId) || "222".equals(userId)) {
            logger.info("拦截自定义黑名单用户 userId：{}", userId);
            return false;
        }
        return true;
    }

}

