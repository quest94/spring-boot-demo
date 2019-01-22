package com.quec1994.business.demo.controller;

import com.quec1994.business.demo.service.IDemoService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <P>ClassName: DemoController</P>
 * <P>Descriprion: 例子业务的控制器类</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/22
 **/
@RestController
@RequiredArgsConstructor
public class DemoController {

    @NonNull private IDemoService demoService;

    @RequestMapping("demo")
    public Map<String, Object> demo() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", Boolean.TRUE);
        result.put("demo", demoService.demo());
        return result;
    }

}
