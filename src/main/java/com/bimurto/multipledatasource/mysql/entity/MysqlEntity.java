package com.bimurto.multipledatasource.mysql.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ENTITY")
public class MysqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

}
