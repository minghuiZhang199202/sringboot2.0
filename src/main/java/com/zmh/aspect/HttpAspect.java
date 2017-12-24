package com.zmh.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <p>@author minghuiZhang.</p>
 * <p>description：</p>
 * <p>date: created in 10:22 2017/12/13</p>
 * <p>modified By: </p>
 */
@Aspect()
@Order(2)
@Component
@Slf4j

public class HttpAspect{

    @Pointcut("execution(public * com.girl.web.GirlController.findAll(..))")
    public void httpAspect(){

    }
    @Before("httpAspect()")
    public void before(JoinPoint joinPoint){

        //接收到请求，记录请求内容
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //记录下请求内容
        log.info("url:{}", request.getRequestURI());
        log.info("http_method:{}", request.getMethod());
        log.info("ip:{}", request.getRemoteAddr());
        log.info("class_method:{}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        log.info("args:{} ", Arrays.toString(joinPoint.getArgs()));
    }
}
