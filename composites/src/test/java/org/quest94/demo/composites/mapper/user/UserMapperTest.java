package org.quest94.demo.composites.mapper.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.quest94.demo.composites.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assertions.assertEquals(5, userList.size(), "");
        userList.forEach(System.out::println);
    }

}