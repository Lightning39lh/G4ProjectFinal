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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class LoginController {
    @Autowired
    LoginService lS;
    
    @GetMapping("/login")
    public String getAllUsers(Model model) {
        Login login = new Login();
        model.addAttribute("login", login);
        return "login";
    }
    
    @PostMapping("/login")
    public String PostUser(Model model, @ModelAttribute ("login") Login login){
        String url = "http://localhost:8080/login";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, login, Login.class);
        /*
        model.addAttribute("users", lS.loadUser(login));*/
        return "redirect:/wallet";
    }
}
