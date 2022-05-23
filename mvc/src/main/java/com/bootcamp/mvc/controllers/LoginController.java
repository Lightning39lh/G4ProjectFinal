/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.mvc.controllers;

import com.bootcamp.mvc.controllers.services.LoginService;
import com.bootcamp.mvc.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    LoginService lS;
    
    @GetMapping("all")
    public String getAllUsers(Model model) {
        Login login = new Login();
        model.addAttribute("users", lS.loadUser(login));
        return "usersIndex";
    }
}
