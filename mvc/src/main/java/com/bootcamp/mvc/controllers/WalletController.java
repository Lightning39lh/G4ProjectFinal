
package com.bootcamp.mvc.controllers;


import com.bootcamp.mvc.controllers.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class WalletController {
    
    @Autowired
    WalletService wS;
    
    @GetMapping("/wallet")
    public String getWallet(Model model) {
        model.addAttribute("wallet", wS.getWallet());
        
        return "wallet";
    }
    //ENDPOINTS API
    /* 
    @GetMapping
    public ArrayList<Wallet> getAllWallets(){
        return wS.getAllWallets();
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
    
    @PostMapping
    public ResponseEntity<String> saveWallet(@RequestBody Wallet wallet){
        wS.saveWallet(wallet);
        return ResponseEntity.ok().body("Success.");
    }

    @PostMapping("/AddToken/{idWallet}/{nameToken}")
    public ResponseEntity<String> addToken(@PathVariable Long idWallet , @PathVariable String nameToken){
        wS.addToken(idWallet, nameToken);
        return ResponseEntity.ok().body("Success.");
    }
    
    @PostMapping("/BuyToken/")
    public ResponseEntity<String> buyToken(@RequestBody Ticket ticket) throws Exception{
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
    
    @PostMapping("/ExchangeToken/")
    public ResponseEntity<Object> exchangeToken(@RequestBody Exchange exchange) throws Exception {
        int e;
        e = wS.exchangeToken(exchange);
        if(e==1)return ResponseEntity.status(200).body("Success.");
        else if(e==2) return ResponseEntity.status(400).body("Fee error");
        else return ResponseEntity.status(400).body("Not enough Token");
    }*/
}
