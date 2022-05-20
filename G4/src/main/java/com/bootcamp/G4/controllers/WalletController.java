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
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    public ResponseEntity saveWallet(@RequestBody Wallet wallet){
        wS.save(wallet);
        return ResponseEntity.ok().body("Success.");
    }
=======
=======
>>>>>>> Stashed changes
    public ResponseEntity<Object> saveWallet(@RequestBody Wallet wallet){
        wS.saveWallet(wallet);
        return ResponseEntity.ok().body("Success.");
    }

    @PostMapping("/AddToken/{idWallet}/{idToken}")
    public ResponseEntity<Object> addToken(@PathVariable Long idWallet , @PathVariable long idToken){
        wS.addToken(idWallet, idToken);
        return ResponseEntity.ok().body("Success.");
    }

    @PostMapping("/BuyToken/")
    public ResponseEntity<Object> buyToken(@RequestBody Ticket ticket) throws Exception{
       wS.buyToken(ticket);
        return ResponseEntity.ok().body("Success.");
    }
    
    @PostMapping("/SellToken/")
    public ResponseEntity<Object> sellToken(@RequestBody Ticket ticket) throws Exception {
        int e;
        e = wS.sellToken(ticket);
        if(e==1)return ResponseEntity.status(200).body("Success.");
        else if(e==2) return ResponseEntity.status(400).body("Fee error");
        else return ResponseEntity.status(400).body("Not enough Token");
    }
    
    @PostMapping("/ChangeToken/{idNuevo}")
    public ResponseEntity<Object> changeToken(@RequestBody Ticket ticket, @PathVariable Long idNuevo) throws Exception {
        int e;
        e = wS.ChangeToken(ticket, idNuevo);
        if(e==1)return ResponseEntity.status(200).body("Success.");
        else if(e==2) return ResponseEntity.status(409).body("Fee error");
        else return ResponseEntity.status(409).body("Not enough Token");
    }
    
>>>>>>> Stashed changes
}
