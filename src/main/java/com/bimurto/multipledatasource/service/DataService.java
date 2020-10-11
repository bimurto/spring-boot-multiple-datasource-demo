package com.bimurto.multipledatasource.service;

import com.bimurto.multipledatasource.mysql.entity.MysqlEntity;
import com.bimurto.multipledatasource.mysql.repository.MysqlRepository;
import com.bimurto.multipledatasource.postgres.entity.PostgresEntity;
import com.bimurto.multipledatasource.postgres.repository.PostgresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DataService {

    private final MysqlRepository mysqlRepository;
    private final PostgresRepository postgresRepository;

    public DataService(MysqlRepository mysqlRepository, PostgresRepository postgresRepository) {
        this.mysqlRepository = mysqlRepository;
        this.postgresRepository = postgresRepository;
    }

    @Transactional
    public void updateMysqlDb(){
        MysqlEntity mysqlEntity = mysqlRepository.findById(1L).orElseThrow(() -> new RuntimeException("Entity Not found with id : 1L"));
        mysqlEntity.setName("MYSQL_"+LocalDateTime.now());
    }

    @Transactional(transactionManager = "postgresTransactionManager")
    public void updatePostgresDb(){
        PostgresEntity postgresEntity = postgresRepository.findById(1L).orElseThrow(() -> new RuntimeException("Entity Not found with id : 1L"));
        postgresEntity.setName("POSTGRES_"+LocalDateTime.now());
    }

    @Transactional
    public void updateMysqlDbWithException(){
        MysqlEntity mysqlEntity = mysqlRepository.findById(1L).orElseThrow(() -> new RuntimeException("Entity Not found with id : 1L"));
        mysqlEntity.setName("MYSQL_"+LocalDateTime.now());
        throw new RuntimeException("Exception for testing trasaction");
    }

    @Transactional(transactionManager = "postgresTransactionManager")
    public void updatePostgresDbWithException(){
        PostgresEntity postgresEntity = postgresRepository.findById(1L).orElseThrow(() -> new RuntimeException("Entity Not found with id : 1L"));
        postgresEntity.setName("POSTGRES_"+LocalDateTime.now());
        throw new RuntimeException("Exception for testing trasaction");
    }

    @Transactional(transactionManager = "chainedTransactionManager")
    public void updateBothDb(){
        MysqlEntity mysqlEntity = mysqlRepository.findById(1L).orElseThrow(() -> new RuntimeException("Entity Not found with id : 1L"));
        mysqlEntity.setName("MYSQL_"+LocalDateTime.now());
        PostgresEntity postgresEntity = postgresRepository.findById(1L).orElseThrow(() -> new RuntimeException("Entity Not found with id : 1L"));
        postgresEntity.setName("POSTGRES_"+LocalDateTime.now());
    }

    @Transactional(transactionManager = "chainedTransactionManager")
    public void updateBothDbWithException(){
        updateBothDb();
        throw new RuntimeException("Exception for testing trasaction");
    }
}
