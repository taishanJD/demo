package com.example.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j //这个需要配合lombok插件使用
public class LogAspect {

    private static final String TAG = "LogAspect";
    /**这里定义本切面生效的地方：切点
     * 用@annotation指定一个注解，在使用了该注解的方法aop会生效
     * 用execution指定一个包，该包下的方法会生效
     * 两者可以用或、且符号相连
     */
    @Pointcut("@annotation(com.example.spring.aop.aspect.OperationLogDetail) " +
            "&& execution(* com.example.spring.mvc.controller.*.*(..))")
    public void operationLog(){}


    /**
     * 在执行方法前后调用Advice，这是最常用的方法，相当于@Before和@AfterReturning全部做的事儿
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //从HttpServletRequest中可以拿到很多关于http请求的信息
        HttpServletRequest request = attributes.getRequest();
        //函数签名，通过放射可以获取具体方法的方法名称，注解属性等
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        //获取注解
        OperationLogDetail logAnnotation = signature.getMethod().getAnnotation(OperationLogDetail.class);
        String detail = logAnnotation.detail();
        //获取请求头中的User-Agent
//        UserAgent userAgent = UserAgentent.parseUserAgentString(request.getHeader("User-Agent"));

        //打印请求的内容
        long startTime = System.currentTimeMillis();
        String URL = request.getRequestURL().toString();
        String type = request.getMethod();
        String ip = request.getRemoteAddr();
        String method = signature.getDeclaringTypeName()+"."+signature.getName();
        String argNames = Arrays.toString(signature.getParameterNames());
        String args = Arrays.toString(pjp.getArgs());
        log.info("-----------------------------------------------------------------------");
        log.info("{},URL:{},type:{},ip:{}",detail,URL,type,ip);
        log.info("method:{},argNames:{},args:{}",method,argNames,args);
// 系统信息
//        log.info("浏览器：{}", userAgent.getBrowser().toString());
//        log.info("浏览器版本：{}",userAgent.getBrowserVersion());
//        log.info("操作系统: {}", userAgent.getOperatingSystem().toString());
        // pjp.proceed()：当我们执行完切面代码之后，还有继续处理业务相关的代码。
        // proceed()方法会继续执行业务代码，并且其返回值，就是业务处理完成之后的返回值。
        Object ret = pjp.proceed();
        log.info("请求结束时间:{},请求耗时:{}ms",LocalDateTime.now(),System.currentTimeMillis() - startTime);
        // 处理完请求，返回内容
        log.info("请求返回:{} ",ret);
        return ret;
    }

    /**
     * 前置通知：
     * 1. 在执行目标方法之前执行，
     * 2. 在前置通知中设置请求日志信息，如开始时间，请求参数，注解内容等
     * */
    @Before("operationLog()")
    public void doBefore(){
        return;
    }

    /**
     * 返回通知：
     * 1. 在目标方法正常结束之后执行
     * 2。在返回通知中补充请求日志信息，如返回时间，方法耗时，返回值，并且保存日志信息
     * @param ret 方法返回值
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "operationLog()")
    public void doAfterReturning(Object ret) throws Throwable {
//        endTime = System.currentTimeMillis();
//        log.info("请求结束时间：{}", LocalDateTime.now());
//        log.info("请求耗时：{}", (endTime - startTime));
//        // 处理完请求，返回内容
//        log.info("请求返回 : {}", ret);
    }

    /**
     * 异常通知：
     * 1. 在目标方法非正常结束，发生异常或者抛出异常时执行
     * 1. 在异常通知中设置异常信息，并将其保存
     *
     * @param throwable
     */
    @AfterThrowing(value = "operationLog()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        // 保存异常日志记录
        log.error("发生异常时间:{},抛出异常:{}", LocalDateTime.now(), throwable.getMessage());
    }



}
