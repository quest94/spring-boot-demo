package org.quest94.demo.composites.rabbit.receive.user.save;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.quest94.demo.composites.entity.user.User;
import org.quest94.demo.composites.rabbit.Constants;
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

    /**
     * 保存用户消息接收类
     *
     * @param user 用户信息
     * @author V1.0, qyz12, 2019/3/2 19:56
     **/
    @RabbitHandler
    public void receiveMessage(User user) {
        log.info("新增一个用户，用户信息为：" + JSONObject.toJSONString(user));
    }

}

