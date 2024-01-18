package org.quest94.demo.sharding.sphere.base.test;

import org.junit.jupiter.api.Test;
import org.quest94.demo.sharding.sphere.config.mybatis.plus.MysqlDdl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TableBuilderTest {

    @Autowired
    MysqlDdl mysqlDdl;

    @Test
    void rebuildTable() {
        mysqlDdl.runScript("db/test-schema.sql");
    }

}
