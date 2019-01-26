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
