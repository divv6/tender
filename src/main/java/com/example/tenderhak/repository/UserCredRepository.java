package com.example.tenderhak.repository;

import org.springframework.data.repository.CrudRepository;

public interface UserCredRepository extends CrudRepository<UserCred, Long> {

    UserCred findByLogin(String login);

}
