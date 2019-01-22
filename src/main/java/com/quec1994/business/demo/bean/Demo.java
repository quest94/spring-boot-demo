package com.quec1994.business.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>ClassName: Demo</P>
 * <P>Descriprion: 例子业务的Bean</P>
 *
 * @author quec1994
 * @version V1.0, 2019/1/22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Demo {
    private String name;
    private Integer age;
}
