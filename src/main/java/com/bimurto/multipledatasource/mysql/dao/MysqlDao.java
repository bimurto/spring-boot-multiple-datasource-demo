package com.bimurto.multipledatasource.mysql.dao;

import com.bimurto.multipledatasource.mysql.entity.MysqlEntity;
import com.bimurto.multipledatasource.postgres.entity.PostgresEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MysqlDao {

    @PersistenceContext
    EntityManager em;

    public List<PostgresEntity> findAll(){
        Query query = em.createQuery("SELECT e FROM MysqlEntity e",MysqlEntity.class);
        return query.getResultList();
    }

    public List<MysqlEntity> findAllNative(){
        Query query = em.createNativeQuery("SELECT * FROM ENTITY",MysqlEntity.class);
        return query.getResultList();
    }
}
