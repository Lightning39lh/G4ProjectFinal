package com.bootcamp.mvc.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    private Long id;

    private List<Cuenta> cuentas;
}
