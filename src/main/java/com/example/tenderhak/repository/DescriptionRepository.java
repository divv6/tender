package com.example.tenderhak.repository;

import org.springframework.data.repository.CrudRepository;

public interface DescriptionRepository extends CrudRepository<Description, Long> {

    Description findByTitle(String title);

}
