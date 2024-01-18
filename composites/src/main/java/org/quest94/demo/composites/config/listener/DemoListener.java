package org.quest94.demo.composites.config.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

/**
 * <P>ClassName: DemoListener
 * <P>Description: Servlet监听器测试类
 *
 * @author quest94
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