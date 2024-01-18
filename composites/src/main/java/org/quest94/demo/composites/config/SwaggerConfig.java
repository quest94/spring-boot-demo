package org.quest94.demo.composites.config;

/**
 * <P>ClassName: SwaggerConfig
 * <P>Description: Swagger设置类
 *
 * @author quec1994
 * @version V1.0, 2019/2/27
 **/
// @Profile({"dev", "test"})// 开发环境和测试环境开启支持
// @EnableSwagger2
// @Configuration
public class SwaggerConfig {

//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
//                // 是否开启
//                .enable(true).select()
//                // 扫描的路径包
//                .apis(RequestHandlerSelectors.basePackage("org.quest94.demo.composites"))
//                // 指定路径处理PathSelectors.any()代表所有的路径
//                .paths(PathSelectors.any()).build().pathMapping("/");
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("SpringBoot-Swagger2集成和使用-demo示例")
//                .description("quec1994")
//                // 作者信息
//                .contact(new Contact("quec1994", "", "quec1994@163.com"))
//                .version("1.0.0")
//                .build();
//    }
}
