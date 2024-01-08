package org.quest94.demo.composites.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <P>ClassName: MockDemoController
 * <P>Description: RESTful API 单元测试
 *
 * @author qyz12
 * @version V1.0, 2019/3/7
 **/
@RestController
public class MockDemoController {

    @GetMapping("/mock")
    public String demo(String msg) {
        return msg;
    }

}
