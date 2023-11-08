package com.cipitech.samples.spring.boot.repositories;

import com.cipitech.samples.spring.boot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
}
