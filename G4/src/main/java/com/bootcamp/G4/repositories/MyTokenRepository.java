package com.bootcamp.G4.repositories;

import com.bootcamp.G4.model.MyToken;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MyTokenRepository extends JpaRepository<MyToken, Long>{
	
	public Optional<MyToken> findByName(String name);
}
