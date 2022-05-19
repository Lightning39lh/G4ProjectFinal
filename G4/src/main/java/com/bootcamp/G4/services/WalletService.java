package com.bootcamp.G4.services;

import com.bootcamp.G4.model.Cuentas;
import com.bootcamp.G4.model.Wallet;
import com.bootcamp.G4.repositories.MyTokenRepository;
import com.bootcamp.G4.repositories.WalletRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    
    @Autowired
    WalletRepository wR;
    
    @Autowired
    MyTokenRepository tR;

    public ArrayList<Wallet> getAllWallets() {
        return (ArrayList<Wallet>) wR.findAll();
    }

    public Wallet saveWallet(Wallet wallet) {
        
            return wR.save(wallet);      
    }

    public Wallet findById(Long id) {
        
        return wR.findById(id).get();
    }
    
    public boolean removeWallet(Long id) {
        try {
            wR.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Wallet addToken(Long idWallet, Long idToken){
        Wallet wallet = findById(idWallet);
        Cuentas cuenta = new Cuentas();
        cuenta.setId_Wallet(idWallet);
        cuenta.setAmount_tokens(0);
        cuenta.setToken(tR.getById(idToken));
        System.out.println(cuenta);
        wallet.getToken_wallet().add(cuenta);
        return wallet;
    }

    public Wallet buyToken(Long idWallet, Long idToken, double cantidad)
    {
        Wallet wallet = findById(idWallet);
        Cuentas cuenta = new Cuentas();
       
        cuenta.setId_Wallet(idWallet);
        cuenta.setToken(tR.getById(idToken));
        cuenta.addToken(idToken, cantidad);
        wallet.getToken_wallet().add(cuenta);
        
        return wallet;
    }
    /*
    public boolean SellToken (Long idWallet, Long idToken, double cantidad)
    {
        
        Wallet wallet = findById(idWallet);
        Cuentas cuenta = new Cuentas();
        cuenta.setId_Wallet(idWallet);
        cuenta.setToken(tR.getById(idToken));
        cuenta.addToken(idToken, cantidad);
        return true;
        
    }*/
}
