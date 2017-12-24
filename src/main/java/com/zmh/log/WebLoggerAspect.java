package com.zmh.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <p>@author minghuiZhang.</p>
 * <p>description：</p>
 * <p>date: created in 9:22 2017/12/13</p>
 * <p>modified By: </p>
 */
@Slf4j
@Aspect
@Order(5)
@Component
public class WebLoggerAspect{

    protected final ThreadLocal<Long> startTime = new ThreadLocal <Long>();

    @Pointcut("execution(public * com.*.web.*.*(..))")
    public void webLog(){
    }
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint)throws Throwable{
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       //接收到请求，记录请求内容
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //记录下请求内容
        //记录下请求内容
        log.info("url:{}", request.getRequestURI());
        log.info("http_method:{}", request.getMethod());
        log.info("ip:{}", request.getRemoteAddr());
        log.info("class_method:{}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        log.info("args:{} ", Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret)throws Throwable{
        //处理完请求，返回内容
        log.info("response:{}", ret);
        log.info("cost time:{} ",(System.currentTimeMillis() - startTime.get()));
    }
    @After("webLog()")
    public void after(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
       log.info("--后置通知--------  "+methodName+"方法执行结束");
    }
    @AfterThrowing(pointcut = "webLog()",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint,Exception ex){
        String methodName = joinPoint.getSignature().getName();
        log.info("调用方法"+methodName+"出现异常："+ ex);
    }
}
