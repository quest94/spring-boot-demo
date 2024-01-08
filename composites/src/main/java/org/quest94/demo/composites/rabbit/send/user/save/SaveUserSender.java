package org.quest94.demo.composites.rabbit.send.user.save;

import com.alibaba.fastjson.JSONObject;
import org.quest94.demo.composites.entity.user.User;
import org.quest94.demo.composites.rabbit.Constants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * <P>ClassName: SaveUserSenderImpl
 * <P>Description: 发送消息统一业务层实现类
 *
 * @author qyz12
 * @version V1.0, 2019/3/1
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class SaveUserSender {
    @NonNull
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送保存用户的消息
     *
     * @param user 发送内容
     * @author V1.0, qyz12, 2019/3/1 16:39
     **/
    public void sendSaveUserMessage(User user) {
        rabbitTemplate.convertAndSend(Constants.SAVE_USER_QUEUE_NAME, user);
        log.info("SendMessageServiceImpl() >>> 发送消息到RabbitMQ, 消息内容: " + JSONObject.toJSONString(user));
    }

}
