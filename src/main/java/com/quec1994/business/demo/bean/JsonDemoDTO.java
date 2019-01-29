package com.quec1994.business.demo.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <P>ClassName: JsonDemoDTO</P>
 * <P>Description: jsondemo请求的传入参数对象</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/26
 **/
@Data
@NoArgsConstructor
public class JsonDemoDTO {
    // 字符串类型的数据
    @NotBlank(message="字符串不能为空")
    private String string;
    // 集合类型的数据
    @NotEmpty(message = "集合不能为空")
    private List<String> list;
}
