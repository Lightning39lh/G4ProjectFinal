package com.bootcamp.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exchange {
    
    private Long id_wallet;
    
    private double priceToken1;
    private String tokenName1;
    private double amount; 
    
    private double priceToken2;
    private String tokenName2;
}
