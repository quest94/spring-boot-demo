package org.quest94.demo.composites.rabbit.receive.fanout.message;

import lombok.extern.slf4j.Slf4j;
import org.quest94.demo.composites.rabbit.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <P>ClassName: FanoutReceiverC
 * <P>Description: fanout.C 队列监听器
 *
 * @author qyz12
 * @version V1.0, 2019/3/2
 **/
@Component
@RabbitListener(queues = Constants.FANOUT_C_QUEUE_NAME)
@Slf4j
public class FanoutReceiverC {

    @RabbitHandler
    public void process(String msg) {
        log.info("FanoutReceiverC  : " + msg);
    }
}
