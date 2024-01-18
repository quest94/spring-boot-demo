package org.quest94.demo.composites.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <P>ClassName: JsonDemoDTO
 * <P>Description: jsondemo请求的传入参数对象
 *
 * @author quec1994
 * @version V1.0, 2019/1/26
 **/
@Data
@NoArgsConstructor
public class JsonDemoDTO {
    // 字符串类型的数据
    @NotBlank(message = "字符串不能为空")
    private String string;
    // 集合类型的数据
    @NotEmpty(message = "集合不能为空")
    private List<String> list;
}
