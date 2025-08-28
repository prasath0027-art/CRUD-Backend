package com.example.prasath_spring.repository;

import com.example.prasath_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
