package com.quec1994.config.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * <P>ClassName: DemoFilter
 * <P>Description: Servlet过滤器配置测试
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
//注册器名称为customFilter,拦截的url为所有,需要注意的是：注册多个过滤器时，无法指定执行顺序的，只能通过约定类名顺序达到指定顺序的目的
@WebFilter(filterName="demoFilter",urlPatterns={"/*"})
@Slf4j
public class DemoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig){
        log.info("filter 初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        log.info("doFilter 请求处理");
        //对request、response进行一些预处理
        // 比如设置请求编码
        // request.setCharacterEncoding("UTF-8");
        // response.setCharacterEncoding("UTF-8");
        // TODO 进行业务逻辑

        // 链路 直接传给下一个过滤器
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("filter 销毁");
    }
}
