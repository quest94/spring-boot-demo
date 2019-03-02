package com.quec1994.rabbit.receive.user.save;

import com.quec1994.rabbit.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <P>ClassName: SaveUserReceiveMessage
 * <P>Description: 保存用户消费类
 *
 * @author qyz12
 * @version V1.0, 2019/3/1
 **/

@Component
@RabbitListener(queues = Constants.SAVE_USER_QUEUE_NAME)
@Slf4j
public class SaveUserReceiver {

    @RabbitHandler
    public void receiveMessage(String id) {
        log.info("新增一个用户，用户id为：" + id);
        //可以添加自定义业务逻辑处理
    }

}

