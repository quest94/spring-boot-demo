package com.quec1994.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quec1994.bean.user.UserModifyReq;
import com.quec1994.bean.user.UserReq;
import com.quec1994.bean.user.UserResp;
import com.quec1994.config.advice.exception.CommonException;
import com.quec1994.entity.user.User;
import com.quec1994.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * <P>ClassName: UserController
 * <P>Description: user控制器
 *
 * @author quec1994
 * @version V1.0, 2019/1/31
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "用户API")
public class UserController {

    @NonNull
    @Qualifier("UserRedisTemplate")
    RedisTemplate<String, User> urt;

    @NonNull IUserService userService;

    @PostMapping("user")
    @ApiOperation(value = "用户新增")
    public Boolean addUser(@Valid @RequestBody UserReq userReq) {
        User user = new User();
        user.setCode(userReq.getCode());
        user.setName(userReq.getName());
        user.setAge(userReq.getAge());
        user.setEmail(userReq.getEmail());
        /* 由于设置了主键策略 id可不用赋值 会自动生成
        user.setId(0L); */
        userService.saveUser(user);
        return Boolean.TRUE;
    }

    @PutMapping("user")
    @ApiOperation(value = "用户修改")
    //更新时 直接删除缓存 以保证下次获取时先从数据库中获取最新数据
    @CacheEvict(value = "DEMO", key = "#userReq.id")
    public Boolean updateUser(@Valid @RequestBody UserModifyReq userReq) {
        if (userReq.getId() == null || "".equals(userReq.getId())) {
            throw new CommonException("0000", "更新时ID不能为空");
        }
        User user = new User();
        user.setCode(userReq.getCode());
        user.setName(userReq.getName());
        user.setId(userReq.getId());
        userService.updateById(user);
        return Boolean.TRUE;
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "用户查询(ID)")
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "String", paramType = "path", example = "111aaa222bbb", required = true)
    // spring cache管理缓存
    @Cacheable(value = "DEMO", key = "#id")
    public UserResp getUser(@PathVariable("id") String id) {
        //查询
        User user = Optional.ofNullable(userService.getById(id))
                .orElseThrow(() -> new CommonException("0001", "用户ID：" + id + "，未找到"));
        return user2UserResp(user);
    }

    @GetMapping("/userDIY/{id}")
    @ApiOperation(value = "用户查询(ID)-手动控制缓存")
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "String", paramType = "path", example = "111aaa222bbb", required = true)
    public UserResp getUserDIY(@PathVariable("id") String id) {
        UserResp userResp;
        User user = urt.opsForValue().get(id);
        if (user != null) {
            userResp = user2UserResp(user);
        } else {
            //查询
            User user2 = Optional.ofNullable(userService.getById(id))
                    .orElseThrow(() -> new CommonException("0001", "用户ID：" + id + "，未找到"));
            userResp = user2UserResp(user2);
            urt.opsForValue().set(id, user2);
        }
        return userResp;
    }

    private UserResp user2UserResp(User user) {
        UserResp.UserRespBuilder builder = UserResp.builder();
        builder.id(user.getId());
        builder.code(user.getCode());
        builder.name(user.getName());
        builder.status(user.getStatus());
        builder.sex(user.getSex());
        return builder.build();
    }

    @GetMapping("/user/page")
    @ApiOperation(value = "用户查询(分页)")
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页", dataType = "int", example = "0", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页大小", dataType = "int", example = "0", paramType = "query", required = true)})
    public IPage pageUser(int current, int size) {
        // 分页
        Page<User> page = new Page<>(current, size);
        return userService.page(page);
    }
}
