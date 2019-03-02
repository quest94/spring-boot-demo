package com.quec1994.rabbit.send.user.save;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * <P>ClassName: DirectMessageService
 * <P>Description: 发送消息统一业务层
 *
 * @author qyz12
 * @version V1.0, 2019/3/1
 * <P>说明: 继承RabbitTemplate.ConfirmCallback接口，而ConfirmCallback接口是用来回调消息发送成功后的方法，
 * 当一个消息被成功写入到RabbitMQ服务端时，会自动的回调RabbitTemplate.ConfirmCallback接口内的confirm方法完成通知
 **/
public interface SaveUserService extends RabbitTemplate.ConfirmCallback {

    /**
     * 发送新注册用户的消息
     *
     * @param message 发送内容
     * @author V1.0, qyz12, 2019/3/1 16:34
     **/
    void sendSaveUserMessage(String message);
}
