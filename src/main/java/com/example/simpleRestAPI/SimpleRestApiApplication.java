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

}
