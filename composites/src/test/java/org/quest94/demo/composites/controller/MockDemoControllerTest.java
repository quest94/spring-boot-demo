package org.quest94.demo.composites.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
// 因为是mock测试，在实际开发过程中，可指定其测试启动时为随机端口，避免了不必要的端口冲突。
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// 测试单一接口时 ，也可利用注解@WebMvcTest 进行单一测试
// @WebMvcTest(DemoController.class)
public class MockDemoControllerTest {

    /* 使用 WebMvcTest 时使用 @autowired mockMvc 是可自动注入的。
    当直接使用SpringBootTest 会提示 注入失败  这里直接示例利用 MockMvcBuilders工具创建
    */
    //@Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext wc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void demo() throws Exception {
        String msg = "this is a mock test";
        MockHttpServletRequestBuilder mockReqBuilder = get("/mock");
        mockReqBuilder.param("msg", msg);
        ResultActions perform = this.mockMvc.perform(mockReqBuilder);
        // 由于配置了 print() 这个ResultHandler，所以控制台会打印相关参数信息
        perform.andDo(print());
        perform.andExpect(status().isOk());
        MvcResult result = perform.andReturn();

        //断言 是否和预期相等
        Assert.assertEquals("\"" + msg + "\"", result.getResponse().getContentAsString());

    }
}