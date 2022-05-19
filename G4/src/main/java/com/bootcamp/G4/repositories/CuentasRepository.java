package com.bootcamp.G4.repositories;

import com.bootcamp.G4.model.Cuentas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentasRepository extends JpaRepository<Cuentas, Long>{
    
}
