package com.example.DTO.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DTO.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
}

