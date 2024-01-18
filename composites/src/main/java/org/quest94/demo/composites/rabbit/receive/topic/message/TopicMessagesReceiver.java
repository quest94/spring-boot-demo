package org.quest94.demo.composites.rabbit.receive.topic.message;

import lombok.extern.slf4j.Slf4j;
import org.quest94.demo.composites.rabbit.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <P>ClassName: TopicMessagesReceiver
 * <P>Description: topic.messages 消费类
 *
 * @author qyz12
 * @version V1.0, 2019/3/2
 **/
@Component
@RabbitListener(queues = Constants.TOPIC_MESSAGES_QUEUE_NAME)
@Slf4j
public class TopicMessagesReceiver {

    @RabbitHandler
    public void process(String msg) {
        log.info("topicMessagesReceiver  : " + msg);
    }
}
