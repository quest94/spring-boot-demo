package other.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * <P>ClassName: CodeGenerator
 * <P>Description: MyBatis Plus代码生成器
 *
 * @author quest94
 * @version V1.0, 2019/1/31
 **/
public class CodeGenerator {

    private static void generator(String modelName, String... tableName) {

        // 全局配置
        String projectPath = System.getProperty("user.dir");
        GlobalConfig gc = new GlobalConfig.Builder()
                .outputDir(projectPath + "/src/test/java")
                .author("quest94")
                .disableOpenDir()
                .build();

        String url = "jdbc:mysql://192.168.195.128:3306/demo?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
        String username = "myuser";
        String password = "userpassword";
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig.Builder(url, username, password).build();

        // 包配置
        PackageConfig pc = new PackageConfig.Builder()
                .moduleName(modelName)
                .parent("org.quest94.demo.composites.generator")
                .build();

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<CustomFile> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        CustomFile customFile = new CustomFile.Builder()
                .templatePath(templatePath)
                .formatNameFunction((TableInfo tableInfo) -> {
                    // 自定义输出文件名
                    return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                })
                .build();
        focList.add(customFile);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig.Builder()
                .customFile(focList)
                .build();

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig.Builder()
                // 配置自定义输出模板
                //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//                 .entity("templates/entity2.java")
                // templateConfig.setService();
                // templateConfig.setController();
                .build();

        // 策略配置
        StrategyConfig strategy = new StrategyConfig.Builder()
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
//        strategy.setEntityLombokModel(true);
//        strategy.setRestControllerStyle(true);
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
                .addInclude(tableName)
//        strategy.setSuperEntityColumns("id");
//        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
                .build();

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator(dsc);
        mpg.global(gc);
        mpg.packageInfo(pc);
        mpg.injection(cfg);
        mpg.template(templateConfig);
        mpg.strategy(strategy);
        mpg.execute(new FreemarkerTemplateEngine());
    }

    public static void main(String[] args) {
        generator("user", "user");
    }
}
