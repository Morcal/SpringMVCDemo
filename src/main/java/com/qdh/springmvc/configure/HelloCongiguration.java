package com.qdh.springmvc.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by lyqdhgo on 2016/5/18.
 * 添加配置类
 * @EnableWebMvc等价于xml中的 <mvc:annotation-driven/>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.qdh.springmvc")
public class HelloCongiguration {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

//    Configuration配置=xml配置

//    <!--指明 controller 所在包，并扫描其中的注解-->
//    <!--<context:component-scan base-package="com.gaussic.controller"/>-->
//    <context:component-scan base-package="com"/>
//
//    <!-- 静态资源(js、image等)的访问 -->
//    <mvc:default-servlet-handler/>
//
//    <!-- 开启注解 -->
//    <mvc:annotation-driven/>
//
//    <!--ViewResolver 视图解析器-->
//    <!--用于支持Servlet、JSP视图解析-->
//    <bean id="jspViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//    <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
//    <property name="prefix" value="/WEB-INF/pages/"/>
//    <property name="suffix" value=".jsp"/>
//    </bean>
}
