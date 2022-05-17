package com.bootcamp.G4.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "my_users")
public class MyUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id_user;
    private String username;  
    private String password; 
    private String email;
    
    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;
}
