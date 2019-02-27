package com.quec1994.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quec1994.bean.user.UserModifyReq;
import com.quec1994.bean.user.UserReq;
import com.quec1994.bean.user.UserResp;
import com.quec1994.config.controllerAdvice.exception.CommonException;
import com.quec1994.entity.User;
import com.quec1994.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <P>ClassName: UserController</P>
 * <P>Description: user控制器</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/31
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "用户API")
//@RequestMapping("/user")
public class UserController {

    @NonNull IUserService userService;

    @PostMapping("user")
    @ApiOperation(value = "用户新增")
    //正常业务时， 需要在user类里面进行事务控制，控制层一般不进行业务控制的。
//    @Transactional(rollbackFor = Exception.class)
    public Boolean addUser(@Valid @RequestBody UserReq userReq) {
        User user = new User();
        user.setCode(userReq.getCode());
        user.setName(userReq.getName());
        user.setAge(userReq.getAge());
        user.setEmail(userReq.getEmail());
        //由于设置了主键策略 id可不用赋值 会自动生成
        //user.setId(0L);
        userService.save(user);
        //事务测试
//        System.out.println(1 / 0);
        return Boolean.TRUE;
    }

    @PutMapping("user")
    @ApiOperation(value = "用户修改")
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
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "String", paramType = "path", example = "111111111", required = true)
    public UserResp getUser(@PathVariable("id") String id) {
        //查询
        User user = userService.getById(id);
        if (user == null) {
            throw new CommonException("0001", "用户ID：" + id + "，未找到");
        }
        return UserResp.builder()
                .id(user.getId())
                .code(user.getCode())
                .name(user.getName())
                .status(user.getStatus())
                .build();
    }

    @GetMapping("/user/page")
    @ApiOperation(value = "用户查询(分页)")
    @ApiImplicitParams({@ApiImplicitParam(name = "current", value = "当前页", dataType = "int", example = "0", paramType = "query", required = true),
            @ApiImplicitParam(name = "size", value = "每页大小", dataType = "int", example = "0", paramType = "query", required = true)})
    public IPage pageUser(int current, int size) {
        // 分页
        Page<User> page = new Page<>(current, size);
        // 不输出总页数
        // page.setSearchCount(false);
        return userService.page(page);
    }
}
