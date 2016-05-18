package com.qdh.springmvc;

import com.qdh.springmvc.configure.HelloCongiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by lyqdhgo on 2016/5/18.
 * 初始化累的简洁写法|最佳写法
 */
public class HelloSimpleInitalizer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                HelloCongiguration.class
        };
    }

    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
