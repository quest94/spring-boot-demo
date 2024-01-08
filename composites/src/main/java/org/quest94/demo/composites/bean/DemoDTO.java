package org.quest94.demo.composites.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <P>ClassName: DemoDTO
 * <P>Description: 例子业务的Bean
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
