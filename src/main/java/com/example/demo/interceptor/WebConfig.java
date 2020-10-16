package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//web拦截器，拦截所有的请求cook
@Configuration
//@EnableWebMvc 这个注解影响静态资源的获取
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionIntercetpor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionIntercetpor).addPathPatterns("/**");

    }
}

