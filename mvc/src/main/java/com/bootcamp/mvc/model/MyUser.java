/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MyUser {
    private Long id;
    private String username;  
    private String password; 
    private String email;

   // @JoinColumn(name = "role_id")
    private Long role_id;

    //@JoinColumn(name = "wallet_id")
    private Long wallet_id;
}
