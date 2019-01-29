package com.quec1994.common.controllerAdvice.Controller.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>ClassName: CommonResp</P>
 * <P>Description: 统一响应实体</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResp {
    private Integer respCode;
    private String respMsg;
}
