package com.quec1994.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quec1994.mapper.user.UserMapper;
import com.quec1994.entity.User;
import com.quec1994.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quec1994
 * @since 2019-01-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
