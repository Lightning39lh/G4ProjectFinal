package com.bootcamp.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    
    private Long id;
    private Long id_wallet;
    private String name_token;
    private double amount;
    private boolean positive;
}
