package com.example.DTO.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DTO.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

