# SpringMVC

## 添加控制器  

### 返回简单字符串、对象等
```
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
```  

### 返回Json

## 添加配置类，@Configuration 每个Configuration文件相当于一个.xml文件。

### xml中写法  
```  
 <!--指明 controller 所在包，并扫描其中的注解-->
    <!--<context:component-scan base-package="com.gaussic.controller"/>-->
    <context:component-scan base-package="com.qdh.springmvc"/>

    <!-- 静态资源(js、image等)的访问 -->
    <mvc:default-servlet-handler/>

    <!-- 开启注解 -->
    <mvc:annotation-driven/>

    <!--ViewResolver 视图解析器-->
    <!--用于支持Servlet、JSP视图解析-->
    <bean id="jspViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
        <property name="prefix" value="/WEB-INF/pages/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
```  

### 在Java配置文件中写法  
```
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.qdh.springmvc")
// @EnableWebMvc等价于xml中的 <mvc:annotation-driven/>
public class HelloCongiguration {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
```  

## 添加初始化类，该类继承WebApplicationInitializer，配置DispatcherServlet；注册Congiguration.class，可以替代web.xml中的任何Spring配置

### 配置方法一  
通常写法  
```
public class HelloInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(HelloCongiguration.class);
        ctx.setServletContext(servletContext);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}
```  
### 配置方法二  
最简最佳写法  
```
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

```  
## 不同环境下的视图解析器，如xml,Json,jsp  
### Json  
```
// 视图解析器使用Spring MappingJackJson2Js onView将对象转化为Json视图
public class JsonViewResolver implements ViewResolver {
    public View resolveViewName(String s, Locale locale) throws Exception {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);
        return view;
    }
}
```

## Introduction

This is a tutorial project of my spring mvc study, using spring 3.2.0.

To run this project, you should create a database called "springdemo", and import springdemo.sql into it.

If you want to watch the whole tutorial, you can go to [Gaussic OSChina](http://my.oschina.net/gaussik/blog/385697) for more detail.

Better use the newest version of IntelliJ IDEA, JDK 1.7_x, and Tomcat 7.x.

If you want to upgrade spring to 4.x, modify pom.xml first.

If you've got any problems caused by this upgration, better look up the documents in [Spring Framework Reference](http://docs.spring.io/spring/docs/4.3.0.BUILD-SNAPSHOT/spring-framework-reference/htmlsingle/).

If you have any questions or opinions, you can create issues here. Thanks :)

Welcome to visit my personal blog: [Gaussic](http://gaussic.top)

---

## 介绍

这是我个人在学习过程中整理的一个SpringMVCDemo项目。

如果要运行这个项目，首先要创建一个"springdemo"数据库，注意选择utf-8格式，然后将springdemo.sql导入到数据库中。

如果需要查看完整的教程，请访问[Gaussic OSChina](http://my.oschina.net/gaussik/blog/385697) 。

最好使用最新版本的 IntelliJ IDEA, JDK 1.7.x，还有Tomcat7.x。

如果你想将Spring版本升级到4.x，可修改 pom.xml。

任何由升级带来的问题，请查阅[Spring Framework Reference](http://docs.spring.io/spring/docs/4.3.0.BUILD-SNAPSHOT/spring-framework-reference/htmlsingle/)。

如果你有什么问题，或者宝贵的意见，可以在Issues中提出，我会及时回应。谢谢关注 :)

欢迎访问我的个人博客首页：[Gaussic](http://gaussic.top)

