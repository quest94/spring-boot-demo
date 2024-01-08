package org.quest94.demo.composites.rabbit.receive.direct.message;

import org.quest94.demo.composites.rabbit.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <P>ClassName: DirectMessageReceiver
 * <P>Description: 路由键方式消费类
 *
 * @author qyz12
 * @version V1.0, 2019/3/1
 **/

@Component
@RabbitListener(queues = Constants.DIRECT_MESSAGE_QUEUE_NAME)
@Slf4j
public class DirectMessageReceiver {

    @RabbitHandler
    public void receiveMessage(String userName) {
        log.info("【Direct消息接收者1】收到的消息为：" + userName);
        //可以添加自定义业务逻辑处理
    }

}

