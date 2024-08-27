package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {

	UserData findByUsername(String username);

}
