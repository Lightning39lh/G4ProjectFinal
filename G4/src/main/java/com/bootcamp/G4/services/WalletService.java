
package com.bootcamp.G4.services;

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
        System.out.println("llego");
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
    /*
    public Wallet addToken(Long idWallet, Long idToken, double cantidad)
    {
        Wallet wallet = getWalletByID(idWallet);
        wallet.TokenWallet.add(idToken,cantidad);
        wR.save(wallet);
        return Wallet;
    }*/
    
}
