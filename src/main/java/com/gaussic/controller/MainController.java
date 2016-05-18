package com.gaussic.controller;

import com.gaussic.Admin;
import com.gaussic.User;
import com.gaussic.UserList;
import com.gaussic.model.UserEntity;
import com.gaussic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.ws.ResponseWrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by dzkan on 2016/3/8.
 * 添加控制器
 */
@Controller
public class MainController {

    private static final String template = "hello.%s";
    private final AtomicLong counter = new AtomicLong();

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    // TODO: 2016/5/16 http://localhost:8080/data?age=23
    @RequestMapping(value = "/data")
    @ResponseBody
    public String toStringTest(int age) {
        return "age" + age;
    }

    // TODO: 2016/5/16 http://localhost:8080/object?name=qdh&password=123456
    @RequestMapping(value = "/object")
    @ResponseBody
    public String toObjectTest(User user) {
        return "User:" + user.toString();
    }

    // TODO: 2016/5/16 http://localhost:8080/object/inner?name=qdh&password=123&info.phone=187&info.email=lyqdhgo@163.com
    @RequestMapping("/object/inner")
    @ResponseBody
    public String toInnerObject(User user) {
        return "User.Inner" + user.toString();
    }

    // TODO: 2016/5/16 http://localhost:8080/userandadmin?name=qdh&password=1234567  user和admin会有相同值
    @RequestMapping("/userandadmin")
    @ResponseBody
    public String twoObject(User user, Admin admin) {
        return "user:" + user.toString() + " admin:" + admin.toString();
    }

    // 区分user
    @InitBinder("user")
    public void initUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    // 区分admin
    // TODO: 2016/5/16  http://localhost:8080/userandadmin?user.name=qdh&admin.name=root&password=123456
    @InitBinder("admin")
    public void initAdmin(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("admin.");
    }

    // List 数据绑定
    // TODO: 2016/5/16 http://localhost:8080/listobj?users.name[0]=qdh&users.name[1]=lzy
    @RequestMapping("/listobj")
    @ResponseBody
    public String toList(UserList users) {
        return users.toString();
    }

    // json 运用DHC模拟post请求
    @RequestMapping("/json")
    @ResponseBody
    public String toJson(@RequestBody User user) {
        return user.toString();
    }

    // DHC 模拟测试
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    @ResponseBody
    public String book(HttpServletRequest request) {
        String contentType = request.getContentType();
        if (contentType == null) {
            return "book.default";
        } else if (contentType.equals("text")) {
            return "book.txt";
        } else if (contentType.equals("html")) {
            return "book.html";
        }
        return "book.default";
    }


    @RequestMapping("/model")
    public String userInfo(Model model, @RequestParam(value = "name", defaultValue = "Guest") String name) {
        model.addAttribute("name", name);
        if ("admin".equals(name)) {
            model.addAttribute("email", "lyqdhgo@163.com");
        } else {
            model.addAttribute("email", "qidh@xinli.com.cn");
        }
        return "userInfo";
    }

    // 返回简单字符串
    @RequestMapping("/string")
    @ResponseBody
    public String returnString(Model model) {
        return "qdh";
    }


    // 返回jsonView 这个不行
    @RequestMapping("/user/add")
    @ResponseBody
    public View add(User user) {
        Map<String, User> params = new HashMap<String, User>();
        params.put("data", new User());
        MappingJackson2JsonView model = new MappingJackson2JsonView();
        model.setAttributesMap(params);
        return model;
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public ModelAndView test() {
        User user = new User();
        user.setName("text");
        user.setPassword("123456");
        Map map = new HashMap();
        map.put("test", user);
        return new ModelAndView(new MappingJackson2JsonView(), map);
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }


    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap) {
        // 查询user表中所有记录
        List<UserEntity> userList = userRepository.findAll();

        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("userList", userList);

        // 返回pages目录下的admin/users.jsp页面
        return "admin/users";
    }

    // get请求，访问添加用户 页面
    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public String addUser() {
        // 返回 admin/addUser.jsp页面
        return "admin/addUser";
    }

    // post请求，处理添加用户请求，并重定向到用户管理页面
    @RequestMapping(value = "/admin/users/addP", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("user") UserEntity userEntity) {
        // 注意此处，post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
        // 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象

        // 数据库中添加一个用户，该步暂时不会刷新缓存
        //userRepository.save(userEntity);
        System.out.println(userEntity.getFirstName());
        System.out.println(userEntity.getLastName());

        // 数据库中添加一个用户，并立即刷新缓存
        userRepository.saveAndFlush(userEntity);

        // 重定向到用户管理页面，方法为 redirect:url
        return "redirect:/admin/users";
    }

    // 查看用户详情
    // @PathVariable可以收集url中的变量，需匹配的变量用{}括起来
    // 例如：访问 localhost:8080/admin/users/show/1 ，将匹配 id = 1
    @RequestMapping(value = "/admin/users/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        UserEntity userEntity = userRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/userDetail";
    }

    // 更新用户信息 页面
    @RequestMapping(value = "/admin/users/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        UserEntity userEntity = userRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/updateUser";
    }

    // 更新用户信息 操作
    @RequestMapping(value = "/admin/users/updateP", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("userP") UserEntity user) {

        // 更新用户信息
        userRepository.updateUser(user.getNickname(), user.getFirstName(),
                user.getLastName(), user.getPassword(), user.getId());
        userRepository.flush(); // 刷新缓冲区
        return "redirect:/admin/users";
    }

    // 删除用户
    @RequestMapping(value = "/admin/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer userId) {

        // 删除id为userId的用户
        userRepository.delete(userId);
        // 立即刷新
        userRepository.flush();
        return "redirect:/admin/users";
    }
}
