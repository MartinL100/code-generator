package org.cbat.codegenerator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author liucong
 * @version 1.0.0
 * @date 2020/10/20 13:44
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new StaticInterceptor())
                .addPathPatterns("/**")//添加拦截路径
                .excludePathPatterns("/static/**")//排除拦截路径
//                .excludePathPatterns("/swagger-ui.html", "/swagger-resources/**", "/v2/**", "/error/**" );
        ;

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + System.getProperty("user.dir") + File.separator + "file" + File.separator);
    }
}
