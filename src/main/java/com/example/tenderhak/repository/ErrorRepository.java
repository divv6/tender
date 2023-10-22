package com.example.tenderhak.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ErrorRepository extends CrudRepository<Error, Long> {

    @Query(value = "select * from error", nativeQuery = true)
    List<Error> findAll();

    @Query(value = "select count(*) from error where error_type_id = :typeId", nativeQuery = true)
    Integer findCountFromTypeId(@Param("typeId") Long typeId);

    @Query(value = "SELECT MAX(date) FROM error WHERE error_type_id = :typeId", nativeQuery = true)
    LocalDateTime findLastErrorDate(@Param("typeId") Long typeId);

    @Query(value = "SELECT COUNT(*) FROM error WHERE error_type_id IN (:typeIds) " +
            "AND date BETWEEN :currentDate AND :nextDate", nativeQuery = true)
    Integer findCountFromErrorTypeIdBetweenDate(@Param("typeIds") List<Long> typeIds, @Param("currentDate") LocalDateTime currentDate, @Param("nextDate") LocalDateTime nextDate);

    @Query(value = "SELECT date FROM error where error_type_id = :typeId order by date desc LIMIT 100", nativeQuery = true)
    LocalDateTime[] findLast100Dates(@Param("typeId") Long typeId);

}
