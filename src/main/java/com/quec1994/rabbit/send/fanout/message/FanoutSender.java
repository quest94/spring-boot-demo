package com.quec1994.rabbit.send.fanout.message;

import com.quec1994.rabbit.Constants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * <P>ClassName: FanoutSender
 * <P>Description: Fanout 消息发送类
 *
 * @author qyz12
 * @version V1.0, 2019/3/2
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class FanoutSender {

    @NonNull RabbitTemplate rabbitTemplate;

    public void send() {
        String msgString = "fanoutSender :hello i am fanout message";
        log.info(msgString);
        // fanout exchage 的route key无效，一般使用""
        this.rabbitTemplate.convertAndSend(Constants.FANOUT_EXCHANGE_EXCHANGE_NAME, "", msgString);

        String msgString1 = "fanoutSender :hello i am fanout message2";
        log.info(msgString);
        this.rabbitTemplate.convertAndSend(Constants.FANOUT_EXCHANGE_EXCHANGE_NAME, "abcd.ee", msgString1);

    }
}
