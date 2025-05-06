package com.main.uniqueidgenerator;

import com.main.uniqueidgenerator.service.SnowflakeIdGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniqueIdGeneratorApplication {

	public static void main(String[] args) {

		SpringApplication.run(UniqueIdGeneratorApplication.class, args);
		SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1,3);
		for (int i=0;i<5;i++){
			System.out.println(generator.nextId());
		}
	}

}
