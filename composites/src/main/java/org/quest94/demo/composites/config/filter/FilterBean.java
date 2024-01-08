package org.quest94.demo.composites.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * <P>ClassName: FilterBean
 * <P>Description: 直接往spring中注册Servlet过滤器
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
@Component
public class FilterBean {

    @Bean
    public Filter demoFilter3() {
        return new DemoFilter3();
    }

}

@Slf4j
class DemoFilter3 implements Filter {

    @Override
    public void init(FilterConfig filterConfig){
        log.info("filter3 初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        log.info("doFilter3 请求处理");
        //对request、response进行一些预处理
        // 比如设置请求编码
        // request.setCharacterEncoding("UTF-8");
        // response.setCharacterEncoding("UTF-8");
        //TODO 进行业务逻辑

        //链路 直接传给下一个过滤器
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("filter3 销毁");
    }
}