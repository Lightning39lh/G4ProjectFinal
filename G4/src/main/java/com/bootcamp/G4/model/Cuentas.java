package com.bootcamp.G4.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name= "Token_wallet")
public class Cuentas {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private double amount_tokens;
    
    private String tokenName;   
    private Long id_Wallet;
    
    public void addToken (double cantidad){
        this.amount_tokens+=cantidad;
    }

    
}
