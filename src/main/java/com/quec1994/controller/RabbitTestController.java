package com.quec1994.controller;

import com.quec1994.common.rabbit.SendMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <P>ClassName: RabbitTestController
 * <P>Description: RabbitMQ测试控制器类
 *
 * @author qyz12
 * @version V1.0, 2019/3/1
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("rabbit")
@Api(tags = "RabbitMQ测试")
public class RabbitTestController {
    @NonNull SendMessageService sendMessageService;

    @GetMapping("oneToOne")
    @ApiOperation(value = "发送一对一消息")
    public Boolean sendMessageOneToOne() {
        sendMessageService.sendDirectMessage("测试哈哈哈");
        return Boolean.TRUE;
    }

    @GetMapping("oneToMany")
    @ApiOperation(value = "发送一对多消息")
    public void sendMessageOneToMany() {
        int len = 10;
        //循环十次，消费者发送十条消息到RabbitMQ
        for (int i = 1; i <= len; i++) {
            sendMessageService.sendDirectMessage("测试哈哈哈" + i);
        }
    }

}
