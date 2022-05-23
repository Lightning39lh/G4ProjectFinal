package com.bootcamp.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {
    private Long id;

    private double amountToken;

    private String tokenName;

    private Long id_wallet;

}
