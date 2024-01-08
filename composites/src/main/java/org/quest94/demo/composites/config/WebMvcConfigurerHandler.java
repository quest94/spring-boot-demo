package org.quest94.demo.composites.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.quest94.demo.composites.config.interceptor.DemoInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * <P>ClassName: WebMvcConfigurer
 * <P>Description: WebMvcConfigurer
 *
 * @author quec1994
 * @version V1.0, 2019/1/28
 **/
@Configuration
@Slf4j
public class WebMvcConfigurerHandler implements WebMvcConfigurer {

    /**
     * 添加自定义拦截器
     *
     * @param registry 拦截器注册器
     * @author V1.0, quec1994, 2019/2/28 11:43
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器 拦截规则
        //多个拦截器时 以此添加 执行顺序按添加顺序
        registry.addInterceptor(getHandlerInterceptor()).addPathPatterns("/*");
    }

    @Bean
    public static HandlerInterceptor getHandlerInterceptor() {
        return new DemoInterceptor();
    }

    /**
     * 整合fastJson为Http消息转换器
     *
     * @param converters Http消息转换器集
     * @author V1.0, quec1994, 2019/2/28 11:36
     **/
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 1、定义一个消息转换对象
        FastJsonHttpMessageConverter msgConverter = new FastJsonHttpMessageConverter();
        // 2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = msgConverter.getFastJsonConfig();
        /*
            QuoteFieldNames                 —— 输出key时是否使用双引号,默认为true 
            UseSingleQuotes                 —— 使用单引号而不是双引号,默认为false
            WriteMapNullValue               —— 是否输出值为null的字段,默认为false 
            WriteNullNumberAsZero           —— 数值字段如果为null,输出为0,而非null 
            WriteNullListAsEmpty            —— List字段如果为null,输出为[],而非null 
            WriteNullStringAsEmpty          —— 字符类型字段如果为null,输出为”“,而非null 
            WriteNullBooleanAsFalse         —— Boolean字段如果为null,输出为false,而非null
            DisableCircularReferenceDetect  —— 消除对同一对象循环引用的问题，默认为false
         */
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect
        );
        // 设置WriteEnumUsingToString
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteEnumUsingToString);
        msgConverter.setFastJsonConfig(fastJsonConfig);
        // 2-1 处理中文乱码问题
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        msgConverter.setSupportedMediaTypes(mediaTypes);
        converters.add(0, msgConverter);
    }
}
