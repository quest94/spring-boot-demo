package org.quest94.demo.guava;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("guava")
@Slf4j
public class WebController {

    @Limit(key = "query", message = "触发接口限流，请重试", permitsPerSeconds = 100)
    @RequestMapping("test")
    public String test() {
        return "请求成功";
    }


}
