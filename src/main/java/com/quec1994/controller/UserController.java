package com.quec1994.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quec1994.entity.User;
import com.quec1994.service.IUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <P>ClassName: UserController</P>
 * <P>Description: user控制器</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/31
 **/
@RestController
@RequiredArgsConstructor
public class UserController {

    @NonNull IUserService userService;

    @GetMapping("getAllUser")
    public String getAllUser() {
        List<User> list = userService.list();
        String result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
