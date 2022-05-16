package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    // spring mvc使用消息转换器从请求体中解析出@ResquestBody参数，及格式化返回@ResponseBody响应
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .dateFormat(new CustomDateFormat());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }

    // spring mvc将使用下边配置的格式化器从请求参数中解析出Date参数
    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new CustomDateTimeFormatter());
    }
}
