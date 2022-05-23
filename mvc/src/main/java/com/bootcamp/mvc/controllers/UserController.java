
package com.bootcamp.mvc.controllers;

import com.bootcamp.mvc.controllers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
    /*
    @Autowired
    UserService uS;
    
    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", uS.getAllUsers());
        return "usersIndex";
    }*/
}
