package com.bootcamp.G4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "Token_wallet")
public class Token_wallet {
    @GeneratedValue(strategy= GenerationType.AUTO)
    private double amount_tokens;
    private MyToken token;
    @ManyToOne
    @JoinColumn(name="id_wallet", nullable= false)
    private Wallet wallet;
}
