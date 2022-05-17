package com.bootcamp.G4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long>{

	public Optional<MyUser> findByUsername(String username);
	public Optional<MyUser> findByRole(String role);
	public Optional<MyUser> findByEmail(String email);
}
