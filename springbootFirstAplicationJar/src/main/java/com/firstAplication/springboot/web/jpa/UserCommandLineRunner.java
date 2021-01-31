package com.firstAplication.springboot.web.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);


	@Autowired
	private UserRepository userRepo;

	// Si se necesita ejecutar algo al inicializar la aplicacion se usa este
	// CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Se ejecuta commandLineRunner");

		userRepo.save(new User("Ranga", "Admin"));
		userRepo.save(new User("Ravi", "User"));
		userRepo.save(new User("Satish", "Admin"));
		userRepo.save(new User("Raghu", "User"));

		for (User user : userRepo.findAll()) {
			log.info(user.toString());
		}

		log.info("Admin users are.....");
		log.info("____________________");
		for (User user : userRepo.findAll()) {// userRepo.findByRole("Admin")
			log.info(user.toString());
		}

	}

}
