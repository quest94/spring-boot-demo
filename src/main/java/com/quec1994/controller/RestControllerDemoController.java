package com.quec1994.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quec1994.bean.JsonDemoDTO;
import com.quec1994.service.IDemoService;
import com.quec1994.common.controllerAdvice.exception.BusinessException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <P>ClassName: DemoController</P>
 * <P>Description: JSON字符串返回例子的控制器类</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/22
 **/
@RestController
@RequiredArgsConstructor
//@Validated
/*
    @Validated：提供了一个分组功能，可以在入参验证时，根据不同的分组采用不同的验证机制，这个网上也有资料，不详述。

    @Valid：作为标准JSR-303规范，还没有吸收分组的功能。
 */
public class RestControllerDemoController {

    @NonNull
    private IDemoService demoService;

    /**
     * 测试例子跳转页面
     *
     * @author V1.0, quec1994, 2019/1/26 11:58
     **/
    @RequestMapping("demo")
    public Map<String, Object> demo() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", Boolean.TRUE);
        result.put("demo", demoService.demo());
        return result;
    }

    /**
     * 测试@RequestBody注解，请求推荐使用post请求，Content-Type参数值为"application/json; charset=UTF-8"
     *
     * @param jsonDemoDTO 前端传参
     * @return 接收到的前端传参
     * @author V1.0, quec1994, 2019/1/26 11:27
     **/
    @PostMapping("RequestBodyDemo")
    public String requestBodyDemo(@Valid @RequestBody JsonDemoDTO jsonDemoDTO, ModelMap modelMap) {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(jsonDemoDTO.toString());
        String mapJakcson = null;
        try {
            mapJakcson = mapper.writeValueAsString(jsonDemoDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mapJakcson;
    }

    @PostMapping("PathVariableDemo/{id}")
    public String pathVariableDemo(@PathVariable("id") String id) {

        return "id" + id;
    }

    /**
     * 测试例子跳转页面
     *
     * @author V1.0, quec1994, 2019/1/26 11:58
     **/
    @RequestMapping("exceptionDemo2/{id}")
    public String exceptionDemo2(@PathVariable int id){
        if (id == 1) {
            throw new RuntimeException("运行时异常");
        } else {
            throw new BusinessException("自定义控制器层异常");
        }
    }

}
