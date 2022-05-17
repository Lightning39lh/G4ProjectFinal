package com.bootcamp.G4.controllers;

import com.bootcamp.G4.model.MyUser;
import com.bootcamp.G4.services.MyUserService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/MyUsers")
public class UserController {
    
    @Autowired
    private MyUserService uS;

    @GetMapping
    public ResponseEntity<ArrayList<MyUser>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(uS.getAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<Optional<MyUser>> findByUserName(@PathVariable("username") String username){
        Optional<MyUser> user = uS.findByUserName(username);
        if(user!=null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody MyUser user){
        uS.saveUser(user);
        return ResponseEntity.ok().body("Success.");
    }
    
    @PutMapping
    public ResponseEntity<String> put(@RequestBody MyUser user){
        if(uS.editByUserName(user)){
            return ResponseEntity.status(HttpStatus.OK).body("Edited Succesfull");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String>delete(@PathVariable("id")long id){
        if(uS.deleteById(id)){
            return ResponseEntity.status(HttpStatus.OK).body("User Deleted");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }
    
}
