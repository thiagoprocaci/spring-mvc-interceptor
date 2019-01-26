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
