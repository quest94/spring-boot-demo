package org.quest94.demo.composites.rabbit.receive.direct.message;

import lombok.extern.slf4j.Slf4j;
import org.quest94.demo.composites.rabbit.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <P>ClassName: DirectMessage2Receiver
 * <P>Description: 路由键方式消费类2
 *
 * @author qyz12
 * @version V1.0, 2019/3/1
 **/

@Component
@RabbitListener(queues = Constants.DIRECT_MESSAGE_QUEUE_NAME)
@Slf4j
public class DirectMessage2Receiver {

    @RabbitHandler
    public void receiveMessage(String userName) {
        log.info("【Direct消息接收者2】收到的消息为：" + userName);
        //可以添加自定义业务逻辑处理
    }

}

