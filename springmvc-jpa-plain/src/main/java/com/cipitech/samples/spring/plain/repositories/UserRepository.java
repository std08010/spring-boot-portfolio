package com.cipitech.samples.spring.plain.repositories;

import com.cipitech.samples.spring.plain.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
}
