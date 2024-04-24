package com.projects.java.posts.data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.projects.java.posts.controllers.AuthenticationRequest;
import com.projects.java.posts.controllers.RegisterRequest;
import com.projects.java.posts.models.Post;
import com.projects.java.posts.models.Role;
import com.projects.java.posts.repositories.PostRepository;
import com.projects.java.posts.repositories.UserRepository;
import com.projects.java.posts.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(SampleDataLoader.class);
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"),
            new RandomService());
    private final Faker faker = new Faker();
    @Override
    public void run(String... args) throws Exception {
        AuthenticationRequest admin = AuthenticationRequest.builder()
                .email("admin@gmail.com")
                .password("000000")
                .build();
        logger.info("Admin token: "+authService.login(admin).getToken());
        logger.info("Loading posts samples...");
/*
        Creating posts with random content

        List<Post> posts2 = IntStream.rangeClosed(1,5)
                .mapToObj(i->Post.builder()
                        .title(faker.book().title())
                        .content(String.format("%s\n%s %s",
                                faker.book().author(),
                                faker.book().genre(),
                                faker.book().publisher()))
                        .user(userRepository.findById(2L).orElseThrow())
                        .build())
                .toList();
        postRepository.saveAll(posts2);
*/
        logger.info("Loading finished");
    }
}
