# Aplicação Spring Interceptor - Implementando Autenticação

## Classes


### Application


```
package com.tbp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

```

### WebConfig

```
package com.tbp.config;

import com.tbp.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(loginInterceptor);
        interceptorRegistration.addPathPatterns("/**");
    }
}

```

### User

```
package com.tbp.repository;

public class User {

    Integer id;
    String username;
    String password;
    String profile;

    public User(String username, String password, String profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}


```

### UserRepository

```
package com.tbp.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {

    static Map<Integer, User> userMap = new HashMap<>();
    static Integer count = 0;

    static {
        User u = new User("user", "123", "Ordinary");
        userMap.put(count, u);
        count++;
    }

    public void save(User user) {
        if(user.getId() == null) {
            user.setId(count);
            count++;
        }
        userMap.put(user.getId(), user);
    }

    public void delete(Integer name) {
        userMap.remove(name);
    }

    public User getById(Integer id) {
        return userMap.get(id);
    }

    public User getByUsername(String username) {
        for(User u : userMap.values()) {
            if(u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

}


```

### UserSession

```
package com.tbp.interceptor;

import com.tbp.repository.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class UserSession {

    User user;

    public void addLoggedUser(User u) {
        this.user = u;
    }

    public User getLoggedUser() {
        return user;
    }

    public void removeLoggedUser() {
        this.user = null;
    }

}


```

### LoginInterceptor

```

package com.tbp.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);


    @Autowired
    UserSession userSession;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        LOGGER.info("Interceptando a requisicao {}", url);
        if(url.contains("/secure") && userSession.getLoggedUser() == null) {
            LOGGER.info("Redirecting to {}", url);
            String loginPage = httpServletRequest.getContextPath() + "/login/doLogin";
            httpServletResponse.sendRedirect(loginPage);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

```

### IndexController

```

package com.tbp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:secure/";
    }
}


```

### LoginController

```

package com.tbp.controller;

import com.tbp.interceptor.UserSession;
import com.tbp.repository.User;
import com.tbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserSession userSession;


    @RequestMapping(value = "doLogin", method = RequestMethod.GET)
    public String loginPage() {
        return "login/doLogin";
    }

    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Map<String, Object> model) {
        User u = userRepository.getByUsername(username);
        if(u != null && u.getPassword().equals(password)) {
            userSession.addLoggedUser(u);
            return "redirect:/secure";
        } else {
            model.put("message", "Login not valid");
            return null;
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        userSession.removeLoggedUser();
        return "login/doLogin";
    }

}


```

### SecureController

```

package com.tbp.controller;

import com.tbp.interceptor.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("secure")
public class SecureController {

    @Autowired
    UserSession userSession;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String main(Map<String, Object> model) {
        model.put("user", userSession.getLoggedUser());
        return "secure/mainPage";
    }

}

```

### UserController

```
package com.tbp.controller;



import com.tbp.repository.User;
import com.tbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository userRepository;



    @RequestMapping(value = "create", method = RequestMethod.GET )
    public String createPage(Map<String, Object> model) {
        model.put("message", null);
        model.put("success", false);
        return "/user/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST )
    public void save(@RequestParam("username") String username,
                     @RequestParam("password") String password,
                     @RequestParam("profile") String profile,
                     Map<String, Object> model ) {
        if(StringUtils.hasText(username) && StringUtils.hasText(password) && StringUtils.hasText(profile)) {
            User user = new User(username, password, profile);
            userRepository.save(user);
            model.put("message", "Your account has been created");
            model.put("success", true);
        } else {
            model.put("message", "Ops! Please, fill all inputs");
            model.put("success", false);
        }
    }

}


```

## JSPs


### doLogin.jsp

```
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import ="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" integrity="sha384-PmY9l28YgO4JwMKbTvgaS7XNZJ30MK9FAZjjzXtlqyZCqBY6X6bXIkM++IkyinN+" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap-theme.min.css" integrity="sha384-jzngWsPS6op3fgRCDTESqrEJwRKck+CILhJVO5VvaAZCq8JYf8HsR/HPpBOOPZfR" crossorigin="anonymous">

</head>
<body>
    <div class="container">
        <h2>
            Login
        </h2>

                <c:if test="${message != null}">
                        <h3>
                            <c:out value="${message}" />
                        </h3>

                </c:if>


                <form method="post" action="<%=request.getContextPath()%>/login/doLogin" class="form-horizontal">
                    <div class="form-group form-group-lg">
                        <label for="username" class="col-sm-2 control-label">Name:</label>
                        <div class="col-sm-10">
                            <input type="text" name="username" id="username" class="form-control" >
                        </div>
                    </div>
                    <div class="form-group form-group-lg">
                        <label for="password" class="col-sm-2 control-label">Password:</label>
                        <div class="col-sm-10">
                            <input type="password" id="password" class="form-control" name="password"  >
                        </div>
                    </div>

                    <div style="float:right">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                </form>

                 <br ><br >
                 <a href="<%=request.getContextPath()%>/user/create">Create your account</a>

    </div>


    <!-- Latest compiled and minified JavaScript -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js" integrity="sha384-vhJnz1OVIdLktyixHY4Uk3OHEwdQqPppqYR8+5mjsauETgLOcEynD9oPHhhz18Nw" crossorigin="anonymous"></script>
</body>
</html>

```

### mainPage.jsp

```
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
          <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
           <!-- Latest compiled and minified CSS -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" integrity="sha384-PmY9l28YgO4JwMKbTvgaS7XNZJ30MK9FAZjjzXtlqyZCqBY6X6bXIkM++IkyinN+" crossorigin="anonymous">

            <!-- Optional theme -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap-theme.min.css" integrity="sha384-jzngWsPS6op3fgRCDTESqrEJwRKck+CILhJVO5VvaAZCq8JYf8HsR/HPpBOOPZfR" crossorigin="anonymous">

    </head>
    <body>
    <div class="container">
        <br> <br>

        <p class="text-success">
            Welcome ${user.username}!
        </p>
        <br> <br>


         <a href="<%=request.getContextPath()%>/login/logout">Logout</a>

         <br> <br>

         <p class="bg-info">
             <c:if test="${user.profile == 'Ordinary'}">
                Ordinary Content
             </c:if>
             <c:if test="${user.profile == 'Moderator'}">
                 Moderator Content
              </c:if>
          </p>

     </div>

     <!-- Latest compiled and minified JavaScript -->
     <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js" integrity="sha384-vhJnz1OVIdLktyixHY4Uk3OHEwdQqPppqYR8+5mjsauETgLOcEynD9oPHhhz18Nw" crossorigin="anonymous"></script>

    </body>
</html>
```

### create.jsp

```
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import ="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" integrity="sha384-PmY9l28YgO4JwMKbTvgaS7XNZJ30MK9FAZjjzXtlqyZCqBY6X6bXIkM++IkyinN+" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap-theme.min.css" integrity="sha384-jzngWsPS6op3fgRCDTESqrEJwRKck+CILhJVO5VvaAZCq8JYf8HsR/HPpBOOPZfR" crossorigin="anonymous">

</head>
<body>
    <div class="container">
        <h2>
            Create your account
        </h2>

                <c:if test="${message != null}">
                        <h3>
                        <c:out value="${message}" />
                              <c:if test="${success == true}">
                                 <br>
                                 <a href="<%=request.getContextPath()%>/login/doLogin">Login here</a>
                              </c:if>
                        </h3>

                </c:if>
               <c:if test="${success == false}">

                <form method="post" action="<%=request.getContextPath()%>/user/create" class="form-horizontal">
                    <div class="form-group form-group-lg">
                        <label for="username" class="col-sm-2 control-label">Name:</label>
                        <div class="col-sm-10">
                            <input type="text" name="username" id="username" class="form-control" >
                        </div>
                    </div>
                    <div class="form-group form-group-lg">
                        <label for="password" class="col-sm-2 control-label">Password:</label>
                        <div class="col-sm-10">
                            <input type="password" id="password" class="form-control" name="password"  >
                        </div>
                    </div>
                    <div class="form-group form-group-lg">
                        <label for="profile" class="col-sm-2 control-label">Profile:</label>
                         <div class="col-sm-10">
                              <select class="form-control" id="profile" name="profile">
                                <option selected>Ordinary</option>
                                <option>Moderator</option>
                              </select>
                          </div>
                    </div>
                    <div style="float:right">
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>

                </c:if>

    </div>


    <!-- Latest compiled and minified JavaScript -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js" integrity="sha384-vhJnz1OVIdLktyixHY4Uk3OHEwdQqPppqYR8+5mjsauETgLOcEynD9oPHhhz18Nw" crossorigin="anonymous"></script>
</body>
</html>

```

## Properties


### application.properties

```

server.context-path: /login-app


spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp


```
