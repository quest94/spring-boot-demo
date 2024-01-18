package org.quest94.demo.composites.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.quest94.demo.composites.entity.user.User;
import org.quest94.demo.composites.mapper.user.UserMapper;
import org.quest94.demo.composites.rabbit.send.user.save.SaveUserSender;
import org.quest94.demo.composites.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>服务实现类
 *
 * @author quest94
 * @since 2019-01-31
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @NonNull
    private SaveUserSender saveUserService;
    @NonNull
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveUser(User user) {
        // 保存用户操作
        // 这里写保存数据库操作...
        userMapper.insert(user);
        // 发送消息到RabbitMQ
        saveUserService.sendSaveUserMessage(user);
        return user.getId();
    }
}
