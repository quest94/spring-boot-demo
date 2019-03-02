package com.quec1994.rabbit.receive.fanout.message;

import com.quec1994.rabbit.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <P>ClassName: FanoutReceiverA
 * <P>Description: fanout.B 队列监听器
 *
 * @author qyz12
 * @version V1.0, 2019/3/2
 **/
@Component
@RabbitListener(queues = Constants.FANOUT_A_QUEUE_NAME)
@Slf4j
public class FanoutReceiverA {

    @RabbitHandler
    public void process(String msg) {
        log.info("FanoutReceiverA  : " + msg);
    }
}
