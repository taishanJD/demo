package com.example.spring.mvc.controller;

import com.example.spring.aop.aspect.OperationLogDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    //myfilter不会拦截，myinterceptor不会进入
    @RequestMapping(value = "/login")
    @ResponseBody
    @OperationLogDetail(detail = "自定义注解测试")
    public String test1(){
        return "login success";
    }

    //myfilter会拦截，myinterceptor会进入
    @RequestMapping(value = "/test")
    @ResponseBody
    public String test2(){
        return "url == /test";
    }

    @RequestMapping(value = "/login/test")
    @ResponseBody
    public String test3(){
        return "url == /login/test";
    }

    @RequestMapping(value = "/anno")
    @ResponseBody
    @OperationLogDetail(detail = "自定义注解测试,带参数")
    public String test4(@RequestParam(value = "name") String name,@RequestParam(value = "age")int age){
        return name + " " + age;
    }

    @RequestMapping(value = "/anno_excep")
    @ResponseBody
    @OperationLogDetail(detail = "自定义注解测试,异常处理 /anno_excep")
    public int test5(@RequestParam(value = "jiadao") String jiadao){
        throw new RuntimeException("一个异常。。");
    }
}
