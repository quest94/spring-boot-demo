package com.quec1994.entity.user;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;

/**
 * <P>ClassName: StatusEnum
 * <P>Description: 用户状态枚举类
 *
 * @author quec1994
 * @version V1.0, 2019/3/1
 **/
@AllArgsConstructor
public enum StatusEnum implements IEnum<Integer> {
    /**
     * 用户账号禁用状态
     */
    DISABLE(0, "禁用"),
    /**
     * 用户账号启用状态
     */
    ENABLE(1, "启用");

    private int value;
    private String paraphrase;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.paraphrase;
    }
}

