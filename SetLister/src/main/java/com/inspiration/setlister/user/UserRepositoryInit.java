package com.inspiration.setlister.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRepositoryInit {

	  private static final Logger log = LoggerFactory.getLogger(UserRepositoryInit.class);

	  @Bean
	  CommandLineRunner initDatabase(UserRepository repository) {

	    return args -> {
	      log.info("Preloading " + repository.save(new User("Wilbert Alberts", "wilbert")));
	      log.info("Preloading " + repository.save(new User("Marielle Aquina", "marielle")));
	    };
	  }
}
