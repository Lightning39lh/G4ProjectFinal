package com.bootcamp.G4.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("MyWallet")
public class WalletController {
    
    @Autowired
    WalletService wS;

    @GetMapping
    public ArrayList<Wallet> getAllWallets(){
        return wS.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable("id") Long id){
        Wallet wallet = wS.findById(id);
        if(wallet!=null){
            return ResponseEntity.status(HttpStatus.OK).body(wallet);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<String> put(@RequestBody Wallet wallet){
        if(wS.put(wallet)){
            return ResponseEntity.status(HttpStatus.OK).body("Edited Succesfull");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @PostMapping
    public ResponseEntity<String> saveWallet(@RequestBody Wallet wallet){
        if(wS.save(wallet)){
            return ResponseEntity.status(HttpStatus.OK).body("Added User");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }


}
