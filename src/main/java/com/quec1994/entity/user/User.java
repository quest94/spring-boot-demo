package com.quec1994.entity.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户实体类
 *
 * @author quec1994
 * @since 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标示
     * 数据库字段: id varchar(32)
     */
    private String id;

    /**
     * 编码
     * 数据库字段: code varchar(30)
     */
    private String code;

    /**
     * 名称
     * 数据库字段: name varchar(30)
     */
    private String name;

    /**
     * 年龄
     * 数据库字段: age int(11)
     */
    private Integer age;

    /**
     * 性别：1 男，2 女
     * 数据库字段: status int(2)
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private SexEnum sex;

    /**
     * 邮箱
     * 数据库字段: email varchar(50)
     */
    private String email;

    /**
     * 状态 1启用 0 停用
     * 数据库字段: status int(2)
     */
    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private StatusEnum status;

    /**
     * 创建时间
     * 数据库字段: gmt_create timestamp
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     * 数据库字段: gmt_modified timestamp
     */
    private LocalDateTime gmtModified;

}
