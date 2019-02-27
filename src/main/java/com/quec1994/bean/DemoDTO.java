package com.quec1994.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>ClassName: DemoDTO</P>
 * <P>Description: 例子业务的Bean</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoDTO {
    private String name;
    private Integer age;
}
