package com.bootcamp.G4.repositories;

import com.bootcamp.G4.model.Cuentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenWalletRepository extends JpaRepository<Cuentas, Long>{

}
