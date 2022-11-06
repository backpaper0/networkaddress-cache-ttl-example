package com.example;

import java.security.Security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {

		String networkaddressCacheTtl = System.getenv("NETWORKADDRESS_CACHE_TTL");
		if (networkaddressCacheTtl != null) {
			String name = "networkaddress.cache.ttl";
			Security.setProperty(name, networkaddressCacheTtl);
		}

		SpringApplication.run(App.class, args);
	}
}
