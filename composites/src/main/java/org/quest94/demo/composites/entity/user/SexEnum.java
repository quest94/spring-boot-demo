package org.quest94.demo.composites.entity.user;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

/**
 * <P>ClassName: SexEnum
 * <P>Description: 性别枚举类
 *
 * @author quest94
 * @version V1.0, 2019/3/1
 **/
@AllArgsConstructor
public enum SexEnum {
    /**
     * 用户性别为男性
     */
    MALE(1, "男性"),

    /**
     * 用户性别为女性
     */
    WOMAN(2, "女性");


    /**
     * 编码
     * <p>
     * 注解EnumValue用于标记数据库存的值是code
     */
    @EnumValue
    private final int code;
    /**
     * 描述
     */
    private final String description;

    @Override
    public String toString() {
        return this.description;
    }

    public static SexEnum getSexEnum(int code) {
        switch (code) {
            case 1:
                return MALE;
            case 2:
                return WOMAN;
            default:
                return null;
        }
    }
}
