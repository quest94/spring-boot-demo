package com.quec1994.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quec1994.entity.User;
import org.springframework.stereotype.Repository;

/**
 * <P>ClassName: UserMapper</P>
 * <P>Description: user映射类</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/31
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
    int deleteByName(String name);
}

