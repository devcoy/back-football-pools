package com.devcoy.football.pools;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FootballPoolsApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(FootballPoolsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "12345678";
		
		for (int i = 0; i < 4; i++) {
			String passwordBcrypt = this.passwordEncoder.encode(password);
			System.out.println(passwordBcrypt);
		}

	}

}
