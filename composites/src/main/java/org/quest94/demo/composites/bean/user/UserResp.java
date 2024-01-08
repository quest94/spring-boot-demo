package org.quest94.demo.composites.bean.user;

import org.quest94.demo.composites.entity.user.SexEnum;
import org.quest94.demo.composites.entity.user.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>ClassName: UserResp
 * <P>Description: 用于前端显示用户信息
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
    @ApiModelProperty(value = "年龄", dataType = "Integer", name = "age", example = "18")
    private int age;
    @ApiModelProperty(value = "性别", dataType = "String", name = "sex", example = "男性")
    private SexEnum sex;
    @ApiModelProperty(value = "邮箱", dataType = "String", name = "email", example = "xxxxx@xxx.com")
    private String email;
    @ApiModelProperty(value = "状态", dataType = "String", name = "status", example = "启用")
    private StatusEnum status;
}
