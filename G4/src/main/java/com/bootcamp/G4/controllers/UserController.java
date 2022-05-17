package com.bootcamp.G4.controllers;

import java.util.ArrayList;

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
    public ResponseEntity<ArrayList<MyUser>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(uS.getAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<MyUser> findByUserName(@PathVariable("username") String username){
        MyUser user = uS.getByUsername(username);
        if(user!=null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody MyUser user){
        if(uS.save(p)){
            return ResponseEntity.status(HttpStatus.OK).body("Added User");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @PutMapping
    public ResponseEntity<String> put(@RequestBody MyUser user){
        if(uS.put(user)){
            return ResponseEntity.status(HttpStatus.OK).body("Edited Succesfull");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }
    
    @DeleteMapping("/{id}")
    public ReponseEntity<String>delete(@PathVariable("id")long id){
        if(uS.delete(id)){
            return ResponseEntity.status(HttpStatus.OK).body("User Deleted");
        }else{
            return ReponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }
    
}
