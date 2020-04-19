package com.example.spring.mvc.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 自定义过滤器测试
 * 注意
 * 1.需要倒入javax-servlet—api.jar 到 tomcat/lib下 以及 到javahome/jre/lib/ext下，否则启动会报错找不到javax.servlet
 * 2.需要在启动类加@ServletComponentScan(basePackages = "com.example.spring.mvc.filter")
 * */
@WebFilter(urlPatterns = "/*",filterName = "myfilter")
@Slf4j
public class MyFilter implements Filter {


    /**
     * 封装，不需要过滤的list列表
     */
    protected static List<Pattern> patterns = new ArrayList<Pattern>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("myfilter init...");

        //这里进行初始化工作，比如可以添加一些不需要过滤的url
        patterns.add(Pattern.compile("login"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("myfilter doFilter()...");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI().substring(request.getContextPath().length());

        if (isInclude(url)){
            //这里匹配不需要规律的url，直接放行
            filterChain.doFilter(request, response);
            log.info("url == "+url+" 放行！");
            return;
        } else {
            //如果匹配到需要过滤的url，在这里进行逻辑处理，比如session等
            log.info("url == "+url+" 被拦截！");
            filterChain.doFilter(request,response); //这里测试 暂时放行
//            HttpSession session = request.getSession();
//            if (session.getAttribute("currentUser") != null){
//                // session存在
//                filterChain.doFilter(request, response);
//                return;
//            } else {
//                // session不存在 准备跳转失败
//                response.sendRedirect("/loginy.do");
//            }
        }

    }

    @Override
    public void destroy() {
        log.info("myfilter destory()...");
    }

    /**
     * 是否需要过滤
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
