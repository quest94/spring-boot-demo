package com.quec1994.common.rabbit.impl;

import com.quec1994.common.rabbit.SendMessageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.quec1994.common.rabbit.Constants.*;

/**
 * <P>ClassName: SendMessageServiceImpl</P>
 * <P>Description: 发送消息统一业务层实现类</P>
 *
 * @author qyz12
 * @version V1.0, 2019/3/1
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class SendMessageServiceImpl implements SendMessageService {
    @NonNull
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息方法
     *
     * @param message 发送内容
     * @author V1.0, qyz12, 2019/3/1 16:39
     **/
    @Override
    public void sendDirectMessage(Object message) {
        //设置回调对象
        rabbitTemplate.setConfirmCallback(this);
        //构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_EXCHANGE_NAME, DIRECT_MESSAGE_QUEUE_ROUTE_KEY, message, correlationData);
        log.info("SendMessageServiceImpl() >>> 发送消息到RabbitMQ, 消息内容: " + message);

    }

    /**
     * 发送保存用户的消息
     *
     * @param message 发送内容
     * @author V1.0, qyz12, 2019/3/1 16:39
     **/
    @Override
    public void sendSaveUserMessage(Object message) {
        //设置回调对象
        rabbitTemplate.setConfirmCallback(this);
        //构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(SAVE_USER_QUEUE_NAME, message, correlationData);
        log.info("SendMessageServiceImpl() >>> 发送消息到RabbitMQ, 消息内容: " + message);
    }

    /**
     * 消息回调确认方法
     *
     * @param correlationData 回调数据
     * @param isSendSuccess   是否发送成功
     * @author V1.0, qyz12, 2019/3/1 16:39
     **/
    @Override
    public void confirm(CorrelationData correlationData, boolean isSendSuccess, String s) {
        log.info("confirm回调方法>>>>>>>>>>>>>回调消息ID为: " + correlationData.getId());
        if (isSendSuccess) {
            log.info("confirm回调方法>>>>>>>>>>>>>消息发送成功");
        } else {
            log.info("confirm回调方法>>>>>>>>>>>>>消息发送失败" + s);
        }
    }

}
