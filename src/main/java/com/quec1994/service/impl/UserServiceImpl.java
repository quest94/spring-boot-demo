package com.quec1994.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quec1994.common.rabbit.SendMessageService;
import com.quec1994.entity.user.User;
import com.quec1994.mapper.user.UserMapper;
import com.quec1994.service.IUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quec1994
 * @since 2019-01-31
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @NonNull
    private SendMessageService sendMessageService;
    @NonNull
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveUser(User user) {
        //保存用户操作
        //这里写保存数据库操作...
        userMapper.insert(user);
        //发送消息到RabbitMQ
        sendMessageService.sendSaveUserMessage(user.getName());
        return user.getId();
    }
}
