package dev.brainfit;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.brainfit.security.CustomUser;
import dev.brainfit.security.MapCustomUserRepository;

@SpringBootApplication
public class BrainfitApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrainfitApplication.class, args);
	}

	// @Bean
	MapCustomUserRepository userRepository() {
		// the hashed password was calculated using the following code
		// the hash should be done up front, so malicious users cannot discover the
		// password
		// PasswordEncoder encoder =
		// PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// String encodedPassword = encoder.encode("password");

		// the raw password is "password"
		String encodedPassword = "{bcrypt}$2a$10$h/AJueu7Xt9yh3qYuAXtk.WZJ544Uc2kdOKlHu2qQzCh/A3rq46qm";

		CustomUser customUser = new CustomUser(1L, "user@example.com", encodedPassword);
		Map<String, CustomUser> emailToCustomUser = new HashMap<>();
		emailToCustomUser.put(customUser.getEmail(), customUser);
		return new MapCustomUserRepository(emailToCustomUser);
	}

}