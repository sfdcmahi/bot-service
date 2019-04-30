package com.bot.service.botservice;

import com.bot.service.botservice.service.TransAccessService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.*;

@SpringBootApplication
public class BotServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotServiceApplication.class ,args);
	}

}
