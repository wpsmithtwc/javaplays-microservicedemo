package com.ibm.ecoddemo.restservice.customers;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@SpringBootApplication
@EntityScan("com.ibm.ecoddemo.restservice.customers")
@EnableJpaRepositories("com.ibm.ecoddemo.restservice.customers")
@PropertySource("classpath:db-config/db-config.properties")
public class RestCustomersService {

	public static void main(String[] args) {
		SpringApplication.run(RestCustomersService.class, args);
	}

	@Bean
	public DataSource dataSource() {
		/* create in-memory database for now.
		 * TODO: replace with a Cloudant DB on Bluemix */
		DataSource dataSource = (new EmbeddedDatabaseBuilder())
				.addScript("classpath:test-db/schema.sql")
				.addScript("classpath:test-db/data.sql").build();

		return dataSource;
	}
}
