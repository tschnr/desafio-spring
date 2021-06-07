package com.mercadolivre.socialmeli;

import com.mercadolivre.socialmeli.model.Users;
import com.mercadolivre.socialmeli.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SocialMeliApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext configurableApplicationContext =
        SpringApplication.run(SocialMeliApplication.class, args);

        UserRepository userRepository = configurableApplicationContext.getBean(UserRepository.class);

        Users myUser1 = new Users("user1", false);
        Users myUser2 = new Users("user2", false);
        Users myUser3 = new Users("user3", false);
        Users myUser4 = new Users("seller1", true);
        Users myUser5 = new Users("seller2", true);

        userRepository.save(myUser1);
        userRepository.save(myUser2);
        userRepository.save(myUser3);
        userRepository.save(myUser4);
        userRepository.save(myUser5);

    }

}
