package org.kwin.management.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.kwin.management.common.CookieConst;
import org.kwin.management.common.RedisConst;
import org.kwin.management.exception.SysUserException;
import org.kwin.management.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class UserAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * org.kwin.management.controller.*Controller.*(..))" + "&& !execution(public * org" +
            ".kwin.management.controller.UserController.*(..))")
    public void verify() {
    }


    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConst.TOKEN);
        if (cookie == null) {
            log.warn("[登陆校验]Cookie中查不到token");
            throw new SysUserException();
        }

        //去redis里查
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConst.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("[登陆校验]Redis中查不到token");
            throw new SysUserException();
        }
    }


}
