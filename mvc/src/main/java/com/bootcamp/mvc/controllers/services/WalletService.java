
package com.bootcamp.mvc.controllers.services;

import com.bootcamp.mvc.model.Wallet;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WalletService {

    public Object getWallet() {
         String url = "http://localhost:8080/MyWallet/1/";
        RestTemplate restTemplate = new RestTemplate();
        Wallet wallet = new Wallet();
        wallet = restTemplate.getForObject(url, Wallet.class);
        return wallet;
    }
}
