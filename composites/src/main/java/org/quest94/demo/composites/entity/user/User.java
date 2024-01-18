package org.quest94.demo.composites.entity.user;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户实体类
 *
 * @author quest94
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
    @JSONField(serializeFeatures = JSONWriter.Feature.WriteEnumUsingToString)
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
    @JSONField(serializeFeatures = JSONWriter.Feature.WriteEnumUsingToString)
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
