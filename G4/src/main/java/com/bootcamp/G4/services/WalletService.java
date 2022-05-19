package com.bootcamp.G4.services;

import com.bootcamp.G4.model.Cuentas;
import com.bootcamp.G4.model.MyToken;
import com.bootcamp.G4.model.Ticket;
import com.bootcamp.G4.model.Wallet;
import com.bootcamp.G4.repositories.CuentasRepository;
import com.bootcamp.G4.repositories.MyTokenRepository;
import com.bootcamp.G4.repositories.TicketRepository;
import com.bootcamp.G4.repositories.WalletRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    
    @Autowired
    WalletRepository wR;
    
    @Autowired
    MyTokenRepository tR;
    
    @Autowired
    CuentasRepository cR;
    
    @Autowired
    TicketRepository ticketR;

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
        cR.save(cuenta);
       // wallet.getToken_wallet().add(cuenta);
        return wallet;
    }

    public void buyToken(Ticket ticket) throws Exception
    {
        ticketR.save(ticket);

        Cuentas cuenta;
        Long cuentaId= cR.findByIdWalletAndToken(ticket.getId_Wallet(),ticket.getId_Token());
        cuenta = cR.findById(cuentaId).get();
        cuenta.addToken(ticket.getId_Token(), ticket.getCantidad());
        cR.save(cuenta);

    }
    
    public int sellToken (Ticket ticket) throws Exception
    {
        ticketR.save(ticket);
        Cuentas cuenta;
        Long cuentaId= cR.findByIdWalletAndToken(ticket.getId_Wallet(),ticket.getId_Token());
        cuenta = cR.findById(cuentaId).get();
        double resultado = cuenta.getAmount_tokens() - ticket.getCantidad();
        if (resultado > 0) {
            Cuentas cuentaUSD;   
            Long cuentaUSDId= cR.findByIdWalletAndToken(ticket.getId_Wallet(),1L); // la cantidad de usd 
            cuentaUSD = cR.findById(cuentaUSDId).get();
            double resultado2 = cuentaUSD.getAmount_tokens() - 0.05;
            if (resultado2 >0){
                cuenta.addToken(ticket.getId_Token(), - ticket.getCantidad());
                cuentaUSD.addToken(1L, - 0.05);
                cR.save(cuenta);
                cR.save(cuentaUSD);
                return 1; //SUCESS
            }          
            else {
               ticketR.deleteById(ticket.getId());
                return 2; //REQUIERE MAS USD PARA FEE  
            }
        }
        else {
            ticketR.deleteById(ticket.getId());
            return 3; //NO TIENE ESE TOKEN SUFICIENTE
        }
    }  
    
    public int ChangeToken (Ticket ticket, long idToken) throws Exception
    {
        ticketR.save(ticket);
        System.out.println(idToken);
        Cuentas cuenta1;
        Long cuenta1Id= cR.findByIdWalletAndToken(ticket.getId_Wallet(),ticket.getId_Token());
        cuenta1 = cR.findById(cuenta1Id).get();
        double resultado = cuenta1.getAmount_tokens() - ticket.getCantidad(); //me fijo si tengo esa cantidad de token
         if (resultado > 0)
         {
              Cuentas cuentaUSD;   
            Long cuentaUSDId= cR.findByIdWalletAndToken(ticket.getId_Wallet(),1L); // la cantidad de usd 
            cuentaUSD = cR.findById(cuentaUSDId).get();
            double resultado2 = cuentaUSD.getAmount_tokens() - 0.05;
            if (resultado2 >0){     
                Cuentas cuenta2;
                Long cuenta2Id= cR.findByIdWalletAndToken(ticket.getId_Wallet(),idToken);
                cuenta2 = cR.findById(cuenta2Id).get();
     
                System.out.println(cuenta2);

                double usdEquivalent = ticket.getCantidad()/cuenta1.getToken().getPrice(); //cantidad a usd
                System.out.println(usdEquivalent);
                
                MyToken newToken = tR.findById(idToken).get(); 
                double newPrice = usdEquivalent/newToken.getPrice();
                System.out.println(newToken.getPrice());
                System.out.println(newPrice);
                cuenta2.addToken(idToken, newPrice);
                
                //le resto lo que saque
                cuenta1.addToken(ticket.getId_Token(), - ticket.getCantidad());
                cuentaUSD.addToken(1L, - 0.05);
                cR.save(cuenta1);
                cR.save(cuentaUSD);
                cR.save(cuenta2);
                return 1; //SUCESS
            }     else {
               ticketR.deleteById(ticket.getId());
                return 2; //REQUIERE MAS USD PARA FEE  
            }
        }
        else {
            ticketR.deleteById(ticket.getId());
            return 3; //NO TIENE ESE TOKEN SUFICIENTE
        }
    }
}
