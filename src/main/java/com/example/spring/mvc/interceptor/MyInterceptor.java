package com.example.spring.mvc.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    //preHandle在Controller方法之前调用
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        log.info("myInterceptor preHandle被调用");
        //在这里可以对进入controller的请求做预处理

        //这里可以拿到请求的参数，进行预处理
        Map ParameterMap =  httpServletRequest.getParameterMap();
        Map reqMap = new HashMap();
        Set<Map.Entry<String,String[]>> entry = ParameterMap.entrySet();
        Iterator<Map.Entry<String,String[]>> it = entry.iterator();
        while (it.hasNext()){
            Map.Entry<String,String[]>  me = it.next();
            String key = me.getKey();
            String value = me.getValue()[0];
            reqMap.put(key,value);
        }
//        String queryString = JSON.toJSONString(reqMap);
        log.info("args = {}",reqMap);

//        Map map =(Map)httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//        log.info
//(map);

        return true; //如果false，停止流程，api被拦截


//        log.info
//(map.get("name"));
//        log.info
//(httpServletRequest.getParameter("username"));
//        if(map.get("name").equals("zhangsan")) {
//            return true;    //如果false，停止流程，api被拦截
//        }else {
//            PrintWriter printWriter = httpServletResponse.getWriter();
//            printWriter.write("please login again!");
//            return false;
//        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //此时还未将modelAndView进行渲染，被拦截的URL对应的controller方法执行后的自定义处理
        log.info("myInterceptor postHandle被调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //此时modelAndView已被渲染，执行拦截器的自定义处理。
        log.info("myInterceptor afterCompletion被调用");

    }
}
