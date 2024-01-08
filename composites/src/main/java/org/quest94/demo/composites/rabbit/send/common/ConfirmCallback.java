package org.quest94.demo.composites.rabbit.send.common;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * <P>ClassName: ConfirmCallback
 * <P>Description: 注册消息发送后的回调
 * <P>说明: 实现RabbitTemplate.ConfirmCallback接口，而ConfirmCallback接口是用来回调消息发送成功后的方法，
 * <P>当一个消息被成功写入到RabbitMQ服务端时，会自动的回调RabbitTemplate.ConfirmCallback接口内的confirm方法完成通知
 *
 * @author qyz12
 * @version V1.0, 2019/3/2
 **/
@RequiredArgsConstructor
@Component
@Slf4j
public class ConfirmCallback implements RabbitTemplate.ConfirmCallback {

    @NonNull
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    @Autowired
    public void init() {
        Assert.notNull(rabbitTemplate, "rabbitTemplate 不能为空");
        rabbitTemplate.setMandatory(true);
        //设置回调对象
        rabbitTemplate.setConfirmCallback(this);
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
        StringBuilder sb = new StringBuilder("confirm回调方法>>>>>>>>>>>>>");
        if (correlationData != null) {
            sb.append("回调消息ID为: ").append(correlationData.getId()).append("，");
        } else {
            sb.append("回调消息无ID，");
        }
        if (isSendSuccess) {
            sb.append("消息发送成功");
        } else {
            sb.append("消息发送失败").append(s);
        }
        log.info(sb.toString());
    }

}
