package com.fundoomicro.useroperations.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fundoomicro.useroperations.model.User;



public interface IUserRepository extends MongoRepository<User, String>{
	Optional<User> findByEmail(String email);
	Optional<User> findByUserId(String id);
}
