package com.example.tenderhak.repository;

import com.example.tenderhak.model.ClassEnum;
import com.example.tenderhak.model.TypeEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeRepository extends CrudRepository<Type, Long> {

    @Query(value = "select * from type", nativeQuery = true)
    List<Type> findAll();

    Type findByType(TypeEnum type);

    List<Type> findAllByClassType(ClassEnum classEnum);

    List<Type> findAllByClassTypeAndType(ClassEnum classEnum, TypeEnum typeEnum);

    @Query(value = "select count(*) from type where class_type = :classType and status = 'DECIDED'", nativeQuery = true)
    Integer findCountSloved(@Param("classType") String classType);

    @Query(value = "select count(*) from type where class_type = :classType and status not in ('DECIDED')", nativeQuery = true)
    Integer findCountUnresolved(@Param("classType") String classType);

}
