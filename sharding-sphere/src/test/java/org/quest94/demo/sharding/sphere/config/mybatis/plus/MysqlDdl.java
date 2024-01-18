package org.quest94.demo.sharding.sphere.config.mybatis.plus;

import mybatis.mate.ddl.IDdlGenerator;
import mybatis.mate.ddl.MysqlDdlGenerator;
import mybatis.mate.ddl.SimpleDdl;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MysqlDdl extends SimpleDdl {

    @Override
    public IDdlGenerator getDdlGenerator() {
        return MysqlDdlGenerator.newInstance();
    }

    /**
     * 执行 SQL 脚本方式
     */
    public void runScript(String sqlFile) {
        // TODO 暂未实现
        super.runScript(dataSource ->{});
    }

}
