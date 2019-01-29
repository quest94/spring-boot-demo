package com.quec1994.business.demo.controller;

import com.quec1994.common.controllerAdvice.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <P>ClassName: ControllerDemoController</P>
 * <P>Description: 普通返回控制器测试</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/26
 **/
@Controller
@RequiredArgsConstructor
public class ControllerDemoController {

    /**
     * 主页跳转页面
     *
     * @return 模板页路径
     * @author V1.0, quec1994, 2019/1/29 21:05
     **/
    @RequestMapping("index")
    public String demo() {
        return "index/index";
    }

    /**
     * 测试例子跳转页面
     *
     * @author V1.0, quec1994, 2019/1/26 11:58
     **/
    @RequestMapping("exceptionDemo1/{id}")
    public String exceptionDemo(@PathVariable int id) throws BusinessException {
        if (id == 1) {
            throw new RuntimeException("运行时异常");
        } else {
            throw new BusinessException("自定义控制器层异常");
        }
    }

}
