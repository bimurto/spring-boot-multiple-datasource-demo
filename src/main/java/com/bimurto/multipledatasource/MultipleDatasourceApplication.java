package com.bimurto.multipledatasource;

import com.bimurto.multipledatasource.mysql.dao.MysqlDao;
import com.bimurto.multipledatasource.mysql.repository.MysqlRepository;
import com.bimurto.multipledatasource.postgres.dao.PostgresDao;
import com.bimurto.multipledatasource.postgres.repository.PostgresRepository;
import com.bimurto.multipledatasource.service.DataService;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class MultipleDatasourceApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(MultipleDatasourceApplication.class, args);
	}

	@Autowired
	DataSource dataSource;

	@Autowired
	@Qualifier("postgresDataSource")
	DataSource postgresDataSource;

	@Autowired
	MysqlRepository mysqlRepository;

	@Autowired
	PostgresRepository postgresRepository;

	@Autowired
	MysqlDao mysqlDao;

	@Autowired
	PostgresDao postgresDao;

	@Autowired
	DataService dataService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		try{
			System.out.println(dataSource.getClass().getName());
			HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
			System.out.println(hikariDataSource.getMinimumIdle());
			System.out.println(hikariDataSource.getMaximumPoolSize());

			hikariDataSource = (HikariDataSource) postgresDataSource;
			System.out.println(hikariDataSource.getMinimumIdle());
			System.out.println(hikariDataSource.getMaximumPoolSize());

			System.out.println(mysqlRepository.findAll());
			System.out.println(postgresRepository.findAll());

			System.out.println(mysqlRepository.findAllNative());
			System.out.println(postgresRepository.findAllNative());

			System.out.println(mysqlDao.findAll());
			System.out.println(postgresDao.findAll());

			System.out.println(mysqlDao.findAllNative());
			System.out.println(postgresDao.findAllNative());

			dataService.updateMysqlDb();
			dataService.updatePostgresDb();

			dataService.updateMysqlDbWithException();
			dataService.updatePostgresDbWithException();

			dataService.updateBothDb();
			dataService.updateBothDbWithException();
		}catch (Exception ex){
			ex.printStackTrace();
		}

	}
}
