package com.bootcamp.G4.controllers;

import com.bootcamp.G4.model.MyToken;
import com.bootcamp.G4.services.MyTokenService;
import java.util.ArrayList;

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
@RequestMapping("/MyToken")
public class TokenController {

    @Autowired
    private MyTokenService tS;

    @GetMapping
    public ResponseEntity<ArrayList<MyToken>> getAllTokens(){
        return ResponseEntity.status(HttpStatus.OK).body(tS.getAllTokens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MyToken> findByTokenId(@PathVariable("id") Long id){
        MyToken token = tS.findByTokenId(id);
        if(token!=null){
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody MyToken token){
        tS.save(token);
        return ResponseEntity.ok().body("Success.");
    }
    
    @PutMapping
    public ResponseEntity<String> put(@RequestBody MyToken token){
        if(tS.edit(token)){
            return ResponseEntity.status(HttpStatus.OK).body("Edited Succesfull");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")long id){
        if(tS.deleteById(id)){
            return ResponseEntity.status(HttpStatus.OK).body("User Deleted");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }
}
