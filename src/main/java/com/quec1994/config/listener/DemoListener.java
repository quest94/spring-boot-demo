package com.quec1994.config.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * <P>ClassName: DemoListener</P>
 * <P>Description: Servlet监听器测试类</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
@WebListener
@Slf4j
public class DemoListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("监听器：销毁");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("监听器：初始化");
    }

}