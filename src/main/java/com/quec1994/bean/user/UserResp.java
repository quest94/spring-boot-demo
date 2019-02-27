package com.quec1994.bean.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>ClassName: UserResp</P>
 * <P>Description: 用于前端显示用户信息</P>
 *
 * @author quec1994
 * @version V1.0, 2019/2/27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "用户信息-显示", description = "用于前端显示用户信息")
public class UserResp {
    @ApiModelProperty(value = "id", dataType = "String", name = "id", example = "111aaa222bbb")
    private String id;
    @ApiModelProperty(value = "编码", dataType = "String", name = "code", example = "u00001")
    private String code;
    @ApiModelProperty(value = "名称", dataType = "String", name = "name", example = "张三")
    private String name;
    @ApiModelProperty(value = "状态", dataType = "String", name = "status", example = "1")
    private String status;
    @ApiModelProperty(value = "年龄", dataType = "Integer", name = "age", example = "18")
    int age;
    @ApiModelProperty(value = "邮箱", dataType = "String", name = "email", example = "xxxxx@xxx.com")
    String email;
}
