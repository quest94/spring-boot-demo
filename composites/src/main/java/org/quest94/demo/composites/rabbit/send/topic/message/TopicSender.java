package org.quest94.demo.composites.rabbit.send.topic.message;

import org.quest94.demo.composites.rabbit.Constants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * <P>ClassName: TopicSender
 * <P>Description: topic.mesaage 生产者
 *
 * @author qyz12
 * @version V1.0, 2019/3/2
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class TopicSender {

    @NonNull
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg1 = "I am \"topic.message.route.key\" msg======";
        log.info("sender1 : " + msg1);
        this.rabbitTemplate.convertAndSend(Constants.TOPIC_EXCHANGE_EXCHANGE_NAME, Constants.TOPIC_MESSAGE_QUEUE_ROUTE_KEY, msg1);

        String msg2 = "I am \"topic.messages.route.key\" msg########";
        log.info("sender2 : " + msg2);
        this.rabbitTemplate.convertAndSend(Constants.TOPIC_EXCHANGE_EXCHANGE_NAME, Constants.TOPIC_MESSAGES_QUEUE_ROUTE_KEY, msg2);

        String msg3 = "I am \"topic.test.route.key\" msg########";
        log.info("sender3 : " + msg3);
        this.rabbitTemplate.convertAndSend(Constants.TOPIC_EXCHANGE_EXCHANGE_NAME, Constants.TOPIC_TEST_QUEUE_ROUTE_KEY, msg3);
    }

}
