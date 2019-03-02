package com.quec1994.controller;

import com.quec1994.rabbit.send.direct.message.DirectMessage2Sender;
import com.quec1994.rabbit.send.direct.message.DirectMessageSender;
import com.quec1994.rabbit.send.fanout.message.FanoutSender;
import com.quec1994.rabbit.send.topic.message.TopicSender;
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

    @NonNull DirectMessageSender directMessageSender;
    @NonNull DirectMessage2Sender directMessage2Sender;
    @NonNull TopicSender topicSender;
    @NonNull FanoutSender fanoutSender;

    @GetMapping("oneToOne")
    @ApiOperation(value = "发送单生产者对单消费者消息")
    public Boolean sendMessageOneToOne() {
        directMessageSender.sendDirectMessage("测试哈哈哈");
        return Boolean.TRUE;
    }

    @GetMapping("oneToMany")
    @ApiOperation(value = "发送单生产者对多消费者消息")
    public void sendMessageOneToMany() {
        int len = 10;
        //循环十次，消费者发送十条消息到RabbitMQ
        for (int i = 1; i <= len; i++) {
            directMessageSender.sendDirectMessage("测试哈哈哈:" + i);
        }
    }

    @GetMapping("/manyToMany")
    @ApiOperation(value = "发送多生产者对多消费者消息")
    public void manyToMany() {
        int len = 10;
        for (int i = 0; i < len; i++) {
            directMessageSender.sendDirectMessage("测试哈哈哈:" + i);
            directMessage2Sender.sendDirectMessage("测试哈哈哈:" + i);
        }
    }

    @GetMapping("/topicTest")
    @ApiOperation(value = "topic exchange类型rabbitmq测试")
    public void topicTest() {
        topicSender.send();
    }

    @GetMapping("/fanoutTest")
    @ApiOperation(value = "fanout exchange类型rabbitmq测试")
    public void fanoutTest() {
        fanoutSender.send();
    }
}
