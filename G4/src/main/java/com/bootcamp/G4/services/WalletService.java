package com.bootcamp.G4.services;

import com.bootcamp.G4.model.Cuentas;
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
        System.out.println("previo"+cuenta);
        cuenta.addToken(ticket.getId_Token(), ticket.getCantidad());
        System.out.println("post "+ cuenta);
        cR.save(cuenta);
 
        

    }
    
    public int sellToken (Ticket ticket) throws Exception
    {
        ticketR.save(ticket);
        System.out.println("ticket");
        //Wallet wallet = findById(ticket.getId_Wallet());
        Cuentas cuenta;
        Long cuentaId= cR.findByIdWalletAndToken(ticket.getId_Wallet(),ticket.getId_Token());
        cuenta = cR.findById(cuentaId).get();
        double resultado = cuenta.getAmount_tokens() - ticket.getCantidad();
        if (resultado > 0) {
            Cuentas cuenta2;   
            Long cuentaId2= cR.findByIdWalletAndToken(ticket.getId_Wallet(),1L); // la cantidad de usd 
            cuenta2 = cR.findById(cuentaId2).get();
            double resultado2 = cuenta2.getAmount_tokens() - 0.05;
            if (resultado2 >0){
                cuenta.addToken(ticket.getId_Token(), - ticket.getCantidad());
                cuenta.addToken(1L, - 0.05);
                cR.save(cuenta);
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
}
