package com.qdh.springmvc.controller;

import com.qdh.springmvc.model.Pizza;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lyqdhgo on 2016/5/18.
 * 控制实体返回的对应的各种数据类型，如json、xml
 */
@Controller
public class Appcontroller {
    @RequestMapping(value = "/pizza/{pizzaName}", method = RequestMethod.GET)
    public String getPizza(@PathVariable String pizzaName, ModelMap model) {
        Pizza pizza = new Pizza(pizzaName);
        model.addAttribute("pizza", pizza);
        return "pizza";
    }
}
