package org.quest94.demo.composites.bean.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * <P>ClassName: UserModifyReq
 * <P>Description: 添加用户时使用的请求类
 *
 * @author quec1994
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

    @ApiModelProperty(value = "状态", dataType = "Integer", name = "status", example = "1", required = true)
    @NotNull(message = "状态不能为空")
    @Range(min = 0, max = 1, message = "状态值为0-1，0为禁用，1为启用")
    private int status;

}
