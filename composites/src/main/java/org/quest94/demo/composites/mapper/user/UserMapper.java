package org.quest94.demo.composites.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.quest94.demo.composites.entity.user.User;
import org.springframework.stereotype.Repository;

/**
 * <P>ClassName: UserMapper
 * <P>Description: user映射类
 *
 * @author quest94
 * @version V1.0, 2019/1/31
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {

}

