package com.bimurto.multipledatasource.postgres.repository;

import com.bimurto.multipledatasource.postgres.entity.PostgresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostgresRepository extends JpaRepository<PostgresEntity, Long> {

    @Query(value = "SELECT * FROM ENTITY", nativeQuery = true)
    List<PostgresEntity> findAllNative();
}
