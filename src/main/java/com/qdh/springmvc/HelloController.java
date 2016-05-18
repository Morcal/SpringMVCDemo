package com.qdh.springmvc;

import com.gaussic.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lyqdhgo on 2016/5/18.
 * 添加控制器
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    // TODO: 2016/5/18 http://localhost:8080/hello/user
    @RequestMapping("/user")
    public String hello(Model model) {
        model.addAttribute("name", "qdh");
        model.addAttribute("password", "123456");
        return "userInfo";
    }

    // 返回简单字符串
    // TODO: 2016/5/18 http://localhost:8080/hello/string 
    @RequestMapping("/string")
    @ResponseBody
    public String returnString(Model model) {
        return "qdh";
    }

    // TODO: 2016/5/18  http://localhost:8080/hello/object?name=qdh&password=123
    @RequestMapping(value = "/object")
    @ResponseBody
    public String toObjectTest(User user) {
        return "User:" + user.toString();
    }
}
