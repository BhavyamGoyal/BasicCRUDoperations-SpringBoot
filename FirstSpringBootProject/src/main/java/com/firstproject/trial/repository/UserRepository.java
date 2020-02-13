package com.firstproject.trial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firstproject.trial.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	

}
