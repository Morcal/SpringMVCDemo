package com.qdh.springmvc.viewresolver;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Locale;

/**
 * Created by lyqdhgo on 2016/5/18.
 * 视图解析器使用Spring MappingJackJson2Js onView将对象转化为Json视图
 */
public class JsonViewResolver implements ViewResolver {
    public View resolveViewName(String s, Locale locale) throws Exception {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);
        return view;
    }
}
