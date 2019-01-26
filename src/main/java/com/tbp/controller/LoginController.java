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

}
