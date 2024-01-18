package org.quest94.demo.composites.rabbit.send.direct.message;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quest94.demo.composites.rabbit.Constants;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <P>ClassName: DirectMessageSenderImpl
 * <P>Description: 直连交换机发送消息代理实现类
 *
 * @author qyz12
 * @version V1.0, 2019/3/1
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class DirectMessage2Sender {

    @NonNull
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息方法
     *
     * @param message 发送内容
     * @author V1.0, qyz12, 2019/3/1 16:39
     **/
    public void sendDirectMessage(Object message) {
        //构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(Constants.DIRECT_EXCHANGE_EXCHANGE_NAME, Constants.DIRECT_MESSAGE_QUEUE_ROUTE_KEY, message, correlationData);
        log.info("DirectMessage2 >>> 发送消息到RabbitMQ, 消息内容: " + message);
    }

}
