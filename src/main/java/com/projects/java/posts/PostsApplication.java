package com.projects.java.posts;

import com.projects.java.posts.controllers.RegisterRequest;
import com.projects.java.posts.services.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.projects.java.posts.models.Role.ADMIN;

@SpringBootApplication
public class PostsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthService authService){
		return args -> {
			var admin = RegisterRequest.builder()
					.name("Admin")
					.email("admin@gmail.com")
					.password("111111")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: "+authService.register(admin).getToken());
		};
	}
}
