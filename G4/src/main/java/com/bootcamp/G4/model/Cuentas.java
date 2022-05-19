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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "Token_wallet")
public class Cuentas {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private double amount_tokens;
    
    @OneToOne
    private MyToken token;   
    private Long id_Wallet;
    
    public double addToken (Long id, double cantidad){
        double resultado = this.amount_tokens+cantidad;
        return resultado;
    }
    
}
