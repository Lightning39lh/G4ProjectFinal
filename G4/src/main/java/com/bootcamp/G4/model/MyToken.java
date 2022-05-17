package com.bootcamp.G4.model;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "Token")
public class MyToken {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id_token;
    private String name;
    private double price;  
}
