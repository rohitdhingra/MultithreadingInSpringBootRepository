package com.example.executor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.executor.api.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
