package com.bimurto.multipledatasource.postgres.dao;

import com.bimurto.multipledatasource.postgres.entity.PostgresEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PostgresDao {

    @PersistenceContext(unitName = "postgresEntityManagerFactory")
    EntityManager em;

    public List<PostgresEntity> findAll(){
        Query query = em.createQuery("SELECT e FROM PostgresEntity e",PostgresEntity.class);
        return query.getResultList();
    }

    public List<PostgresEntity> findAllNative(){
        Query query = em.createNativeQuery("SELECT * FROM ENTITY",PostgresEntity.class);
        return query.getResultList();
    }
}
