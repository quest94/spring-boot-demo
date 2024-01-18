package org.quest94.demo.sharding.sphere.config.mybatis.plus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * 测试，自定义元对象字段填充控制器，实现公共字段自动写入
 */
@Slf4j
public class MysqlMetaObjectHandler implements MetaObjectHandler {

    /**
     * 测试 user 表 name 字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("*************************");
        log.debug("insert of mysql fill");
        log.debug("*************************");
        // 测试下划线
        Object createDatetime = this.getFieldValByName("createDatetime", metaObject);
        log.debug("createDatetime=" + createDatetime);
        if (createDatetime == null) {
            log.warn("strictInsertFill createDatetime1、createDatetime");
            // 测试实体没有的字段，配置在公共填充，不应该set到实体里面
            this.strictInsertFill(metaObject, "createDatetime1", LocalDateTime.class, LocalDateTime.now())
                    .strictInsertFill(metaObject, "createDatetime", LocalDateTime.class, LocalDateTime.now());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("*************************");
        log.debug("update of mysql fill");
        log.debug("*************************");
        Object updateDatetime = this.getFieldValByName("updateDatetime", metaObject);
        log.debug("updateDatetime=" + updateDatetime);
        if (updateDatetime == null) {
            log.warn("strictUpdateFill updateDatetime1、updateDatetime");
            // 测试实体没有的字段，配置在公共填充，不应该set到实体里面
            this.strictUpdateFill(metaObject, "updateDatetime1", LocalDateTime.class, LocalDateTime.now())
                    .strictUpdateFill(metaObject, "updateDatetime", LocalDateTime.class, LocalDateTime.now());
        }
    }
}

