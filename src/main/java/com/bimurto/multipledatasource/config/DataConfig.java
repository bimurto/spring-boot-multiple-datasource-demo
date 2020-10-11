package com.bimurto.multipledatasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataConfig {
    @Bean(name = "chainedTransactionManager")
    public ChainedTransactionManager transactionManager (
            @Qualifier("mysqlTransactionManager") PlatformTransactionManager mysqlTransactionManager,
            @Qualifier("postgresTransactionManager") PlatformTransactionManager postgresTransactionManager) {
        return new ChainedTransactionManager(postgresTransactionManager, mysqlTransactionManager);
    }

}
