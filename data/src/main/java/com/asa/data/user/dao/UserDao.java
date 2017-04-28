package com.asa.data.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asa.data.user.model.User;

public interface UserDao extends JpaRepository<User, Long>{

}
