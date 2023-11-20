package com.cipitech.samples.spring.mongo.repository;

import com.cipitech.samples.spring.mongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{
	boolean existsByEmail(String email);

	@Query("{ 'username' : ?0 }")
	Optional<User> findByName(String name);
}
