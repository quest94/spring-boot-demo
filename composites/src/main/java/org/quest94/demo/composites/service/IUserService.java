package org.quest94.demo.composites.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.quest94.demo.composites.entity.user.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务类
 *
 *
 * @author quest94
 * @since 2019-01-31
 */
public interface IUserService extends IService<User> {

    @Transactional(rollbackFor = Exception.class)
    String saveUser(User user);
}
