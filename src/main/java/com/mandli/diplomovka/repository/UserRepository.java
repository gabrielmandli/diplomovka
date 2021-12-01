package com.mandli.diplomovka.repository;

import org.springframework.data.repository.CrudRepository;

import com.mandli.diplomovka.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
