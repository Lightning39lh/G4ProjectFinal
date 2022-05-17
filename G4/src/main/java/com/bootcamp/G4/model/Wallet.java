package com.bootcamp.G4.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id_wallet;
    @OneToMany(mappedBy= "wallets")
    private ArrayList<TokenWallet> token_wallet;
    
}
