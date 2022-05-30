package com.bootcamp.G4.services;

import com.bootcamp.G4.model.Cuentas;
import com.bootcamp.G4.model.Exchange;

import com.bootcamp.G4.model.Ticket;
import com.bootcamp.G4.model.TokenReducido;
import com.bootcamp.G4.model.Wallet;
import com.bootcamp.G4.repositories.CuentasRepository;

import com.bootcamp.G4.repositories.TicketRepository;
import com.bootcamp.G4.repositories.WalletRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    
    @Autowired
    WalletRepository wR;

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

    public void addToken(TokenReducido tokenReducido){
        if (cR.findByIdWalletAndToken(tokenReducido.getId_Wallet(),tokenReducido.getTokenName())== null) {
        Cuentas cuenta = new Cuentas();
        cuenta.setId_Wallet(tokenReducido.getId_Wallet());
        cuenta.setAmount_tokens(0);
        cuenta.setTokenName(tokenReducido.getTokenName());
        System.out.println(cuenta);      
        cR.save(cuenta);
        }
    }

    public void buyToken(Ticket ticket) throws Exception
    {
        ticket.setPositive(true);
        ticketR.save(ticket);
        Cuentas cuenta;
        Long cuentaId= cR.findByIdWalletAndToken(ticket.getId_wallet(),ticket.getName_token());          
        cuenta = cR.findById(cuentaId).get();
        cuenta.addToken(ticket.getAmount());
        
        cR.save(cuenta);

    }
    
    public int sellToken (Ticket ticket) throws Exception
    {
        ticket.setPositive(false);
        ticketR.save(ticket);
        Cuentas cuenta;
        Long cuentaId= cR.findByIdWalletAndToken(ticket.getId_wallet(),ticket.getName_token());
        
        cuenta = cR.findById(cuentaId).get();
        
        double resultado = cuenta.getAmount_tokens() - ticket.getAmount();
        System.out.println(resultado);
        if (resultado > 0) {
            Cuentas cuentaUSD;   
            Long cuentaUSDId= cR.findByIdWalletAndToken(ticket.getId_wallet(),"USDT"); // la cantidad de usd 
            cuentaUSD = cR.findById(cuentaUSDId).get();
            double resultado2 = cuentaUSD.getAmount_tokens() - 0.05;
            System.out.println(resultado2);
            if (resultado2 >0){
                cuenta.addToken(- ticket.getAmount());
                cuentaUSD.addToken(- 0.05);
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
    
    public int exchangeToken (Exchange exchange) throws Exception
    {     
        Cuentas cuenta1;
        Long cuenta1Id= cR.findByIdWalletAndToken(exchange.getId_wallet(),exchange.getTokenName1());
        cuenta1 = cR.findById(cuenta1Id).get();
        double resultado = cuenta1.getAmount_tokens() - exchange.getAmount(); //me fijo si tengo esa cantidad de token
         if (resultado > 0)
         {
            Cuentas cuentaUSD;   
            Long cuentaUSDId= cR.findByIdWalletAndToken(exchange.getId_wallet(),"USDT"); // la cantidad de usd 
            cuentaUSD = cR.findById(cuentaUSDId).get();
            double resultado2 = cuentaUSD.getAmount_tokens() - 0.05;
            if (resultado2 >0){ 
                Cuentas cuenta2;
                Long cuenta2Id= cR.findByIdWalletAndToken(exchange.getId_wallet(),exchange.getTokenName2());
                cuenta2 = cR.findById(cuenta2Id).get();
                double usdEquivalent = exchange.getAmount()*exchange.getPriceToken1(); //cantidad a usd
                double newAmount = usdEquivalent/exchange.getPriceToken2()*0.995;  
                //BUY
                Ticket buyTicket = new Ticket(exchange.getId_wallet(), exchange.getTokenName2(), newAmount);  
                buyToken(buyTicket);      
                //SELL
                Ticket sellTicket = new Ticket(exchange.getId_wallet(), exchange.getTokenName1(), exchange.getAmount());
                sellToken(sellTicket);
                return 1; //SUCESS
            }     else {

                return 2; //REQUIERE MAS USD PARA FEE  
            }
        }
        else {
            return 3;
             //NO TIENE ESE TOKEN SUFICIENTE
        }
    }
}
