package com.example.simpleRestAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = "com.example.simpleRestAPI")
@Configuration
public class SimpleRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleRestApiApplication.class, args);
	}

	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create().driverClassName("org.h2.Driver").username("sa").password("").url("jdbc:h2:mem:simpleAPI_inMemory").build();
	}
}
