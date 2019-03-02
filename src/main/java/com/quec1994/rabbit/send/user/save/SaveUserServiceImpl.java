package com.quec1994.rabbit.send.user.save;

import com.quec1994.rabbit.Constants;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <P>ClassName: SendMessageServiceImpl
 * <P>Description: 发送消息统一业务层实现类
 *
 * @author qyz12
 * @version V1.0, 2019/3/1
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class SaveUserServiceImpl implements SaveUserService {
    @NonNull
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送保存用户的消息
     *
     * @param id 发送内容
     * @author V1.0, qyz12, 2019/3/1 16:39
     **/
    @Override
    public void sendSaveUserMessage(String id) {
        //设置回调对象
        rabbitTemplate.setConfirmCallback(this);
        //构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(Constants.SAVE_USER_QUEUE_NAME, (Object) id, correlationData);
        log.info("SendMessageServiceImpl() >>> 发送消息到RabbitMQ, 消息内容: " + id);
    }

    /**
     * 消息回调确认方法
     *
     * @param correlationData 回调数据
     * @param isSendSuccess   是否发送成功
     * @author V1.0, qyz12, 2019/3/1 16:39
     **/
    @Override
    public void confirm(CorrelationData correlationData, boolean isSendSuccess, String s) {
        log.info("confirm回调方法>>>>>>>>>>>>>回调消息ID为: " + correlationData.getId());
        if (isSendSuccess) {
            log.info("confirm回调方法>>>>>>>>>>>>>消息发送成功");
        } else {
            log.info("confirm回调方法>>>>>>>>>>>>>消息发送失败" + s);
        }
    }

}
