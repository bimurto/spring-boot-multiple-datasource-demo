package com.bimurto.multipledatasource.mysql.repository;

import com.bimurto.multipledatasource.mysql.entity.MysqlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MysqlRepository extends JpaRepository<MysqlEntity, Long> {
    @Query(value = "SELECT * FROM ENTITY", nativeQuery = true)
    List<MysqlEntity> findAllNative();
}
