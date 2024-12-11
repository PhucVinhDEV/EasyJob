package com.example.EasyJob;


import com.example.EasyJob.user.model.User;
import com.example.EasyJob.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@AllArgsConstructor
public class EasyJobApplication {

	private final UserRepository userRepository;



	public static void main(String[] args) {
		SpringApplication.run(EasyJobApplication.class, args);
		System.out.println("http://localhost:8888/bitznomad/swagger");

	}

//	@Override
//	public void run(String... args) throws Exception {
//		// Initialize data here
//		initializeData();
//	}
//
//	private void initializeData() {
//		// Example: Create and save a user
//		User user = new User("example@example.com", "password", "John Doe", "123456789", "profile-img", "2 years", User.Gender.MALE, User.StatusVerified.VERIDIED, null);
//		userRepository.save(user);
//
//		// Add more data initialization if needed
//		System.out.println("Initial data has been inserted.");
//	}

}
