package com.tbp.controller;


import com.tbp.repository.User;
import com.tbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    public String createPage() {
        return "/user/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST )
    public void save(@RequestParam("name") String name,
                     @RequestParam("password") String password,
                     @RequestParam("profile") String profile,
                     Map<String, Object> model ) {
        User user = new User(name, password, profile);
        userRepository.save(user);
        model.put("message", "Your account has been created");

    }

}
