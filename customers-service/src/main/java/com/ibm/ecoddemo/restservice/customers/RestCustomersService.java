package com.ibm.ecoddemo.restservice.customers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
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
		 * TODO: replace with a cloud db */
		DataSource dataSource = (new EmbeddedDatabaseBuilder())
				.addScript("classpath:test-db/schema.sql")
				.addScript("classpath:test-db/data.sql").build();

		/* retrieve all account numbers */
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> accounts = jdbcTemplate
				.queryForList("SELECT number FROM CUSTOMER");

		/* update each account with a random balance */
		Random rand = new Random();

		for (Map<String, Object> item : accounts) {
			String number = (String) item.get("number");
			BigDecimal balance = new BigDecimal(rand.nextInt(10000000) / 100.0)
					.setScale(2, BigDecimal.ROUND_HALF_UP);
			jdbcTemplate.update(
					"UPDATE ACCOUNT SET balance = ? WHERE number = ?",
					balance, number);
		}

		return dataSource;
	}
}
