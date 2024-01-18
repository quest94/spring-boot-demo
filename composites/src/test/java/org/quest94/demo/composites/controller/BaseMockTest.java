package org.quest94.demo.composites.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * <P>ClassName: BaseMockTest
 * <P>Description: Mock测试基类，用于创建MockMvc对象，这样还需要编写mock测试类时，继承此基类即可。
 *
 * @author qyz12
 * @version V1.0, 2019/3/7
 **/
public class BaseMockTest {

    @Autowired
    private WebApplicationContext wc;

    protected MockMvc mockMvc;

    @BeforeEach
    public void beforeSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
    }
}
