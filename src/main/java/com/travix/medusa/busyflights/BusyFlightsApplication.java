package com.travix.medusa.busyflights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

@SpringBootApplication
public class BusyFlightsApplication {

	public static void main(String[] args) {

		SpringApplication.run(BusyFlightsApplication.class, args);
	}


	@Bean
	RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
