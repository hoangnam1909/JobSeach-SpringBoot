package com.nhn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class JobSearchApplication {

	public static void main(String[] args) {
		System.err.println(SpringVersion.getVersion());
		SpringApplication.run(JobSearchApplication.class, args);
	}

}
