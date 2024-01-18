package org.quest94.demo.sharding.sphere.config.mybatis.plus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.List;

/**
 * Mybatis Plus Config
 */
@Configuration
@MapperScan("org.quest94.demo.sharding.sphere.mapper")
public class MybatisPlusConfig {

    @Bean
    public GlobalConfig globalConfig() {

        DefaultSqlInjector logicSqlInjector = new DefaultSqlInjector() {
            /**
             * 注入自定义全局方法
             */
            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
                List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
                // 不要逻辑删除字段, 不要乐观锁字段, 不要填充策略是 UPDATE 的字段
                methodList.add(new InsertBatchSomeColumn(t -> !t.isLogicDelete() && !t.isVersion() && t.getFieldFill() != FieldFill.UPDATE));
                // 不要填充策略是 INSERT 的字段, 不要字段名是 column4 的字段
                methodList.add(new AlwaysUpdateSomeColumnById(t -> t.getFieldFill() != FieldFill.INSERT && !t.getProperty().equals("column4")));
                return methodList;
            }
        };

        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig()
                // 让 MybatisPlus 不自动对 id 字段赋值
                .setIdType(IdType.AUTO)
                .setColumnFormat("`%s`");

        GlobalConfig conf = new GlobalConfig();
        conf.setDbConfig(dbConfig);
        conf.setSqlInjector(logicSqlInjector);
        return conf;
    }

    @Bean("mybatisSqlSession")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, GlobalConfig globalConfig) throws Exception {

        /* 自动填充插件 */
        globalConfig.setMetaObjectHandler(new MysqlMetaObjectHandler());

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        /* 驼峰转下划线 */
        configuration.setMapUnderscoreToCamelCase(true);
        /* map 下划线转驼峰 */
        configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());

        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        /* 分页 */
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        /* 乐观锁 */
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        /* 数据源 */
        sqlSessionFactory.setDataSource(dataSource);
        /* 枚举扫描 */
//        sqlSessionFactory.setTypeEnumsPackage("org.quest94.demo.sharding.sphere.enums");
        /* xml扫描 */
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mapper/*.xml"));
        /* 扫描 typeHandler */
//        sqlSessionFactory.setTypeHandlersPackage("org.quest94.demo.sharding.sphere.type");
        sqlSessionFactory.setGlobalConfig(globalConfig);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(mybatisPlusInterceptor);
        return sqlSessionFactory.getObject();
    }

}
