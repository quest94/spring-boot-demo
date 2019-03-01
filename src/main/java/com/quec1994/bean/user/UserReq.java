package com.quec1994.bean.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <P>ClassName: UserReq</P>
 * <P>Description: 添加用户前端传入参数</P>
 *
 * @author quec1994
 * @version V1.0, 2019/2/27
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "用户信息-传入", description = "需要传入后端的用户信息")
public class UserReq {

    @ApiModelProperty(value = "编码", dataType = "String", name = "code", example = "u001", required = true)
    @NotBlank(message = "编码不能为空")
    String code;

    @ApiModelProperty(value = "名称", dataType = "String", name = "name", example = "张三", required = true)
    @NotBlank(message = "名称不能为空")
    String name;

    @ApiModelProperty(value = "年龄", dataType = "Integer", name = "age", example = "18", required = true)
    @NotNull(message = "年龄不能为空")
    @Min(value = 1L, message = "年龄不能小于1")
    int age;

    @ApiModelProperty(value = "性别", dataType = "Integer", name = "sex", example = "1", required = true)
    @NotNull(message = "性别不能为空")
    @Range(min = 1L, max = 2L, message = "性别值为1-2，1为男性，2为女性")
    private int sex;

    @ApiModelProperty(value = "邮箱", dataType = "String", name = "email", example = "xxxxx@xxx.com")
    @Email(regexp = ".+@.+\\..+", message = "邮箱格式不正确")
    String email;


}
