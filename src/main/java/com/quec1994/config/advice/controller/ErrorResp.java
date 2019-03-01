package com.quec1994.config.advice.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>ClassName: ErrorResp</P>
 * <P>Description: 发生错误时的统一响应实体</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)// 属性为null时，不参与序列化
@ApiModel(value = "错误信息", description = "传到前端显示的错误信息")
class ErrorResp {
    @ApiModelProperty(value = "编码(119为预想之内的错误，999为预想之外的错误)", dataType = "Integer", name = "code", example = "119")
    private Integer code;
    @ApiModelProperty(value = "错误消息提示字符串", dataType = "String", name = "msg", example = "id不能为空")
    private String msg;
}
