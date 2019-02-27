package com.quec1994.bean.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <P>ClassName: UserModifyReq</P>
 * <P>Description: 添加用户时使用的请求类</P>
 *
 * @author qyz12
 * @version V1.0, 2019/2/28
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "用户信息-传入修改", description = "修改用户时，需要传入后端的用户信息", parent = UserReq.class)
public class UserModifyReq extends UserReq {

    @ApiModelProperty(value = "ID", dataType = "String", name = "id", example = "111aaa222bbb", required = true)
    @NotBlank(message = "ID不能为空")
    private String id;

    @ApiModelProperty(value = "邮箱", dataType = "String", name = "email", example = "xxxxx@xxx.com", required = true)
    @NotBlank(message = "邮箱不能为空")
    @Email(regexp = ".+@.+\\..+", message = "邮箱格式不正确")
    private String email;
}
