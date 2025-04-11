package com.learningcrew.linkup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.learningcrew.linkup")
@SpringBootApplication
public class LinkupApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkupApplication.class, args);
	}

}
