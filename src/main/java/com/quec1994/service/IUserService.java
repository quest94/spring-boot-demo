package com.quec1994.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quec1994.entity.user.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quec1994
 * @since 2019-01-31
 */
public interface IUserService extends IService<User> {

    @Transactional(rollbackFor = Exception.class)
    String saveUser(User user);
}
