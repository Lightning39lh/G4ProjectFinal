package com.bootcamp.G4.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private UserService uS;

    @GetMapping
    public List<User> getAll(){
        return Us.getAll();
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody User user){
        uS.save(user);
        return ResponseEntity.ok().body("Success.");
    }

    @GetMapping("/{username}")
    public User getByEmail(@PathVariable("username") String username){
        
    }
}
