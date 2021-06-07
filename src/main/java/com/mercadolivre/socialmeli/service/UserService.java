package com.mercadolivre.socialmeli.service;


import com.mercadolivre.socialmeli.exception.UserDoesNotExistsException;
import com.mercadolivre.socialmeli.model.Users;
import com.mercadolivre.socialmeli.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Users findByUserId(Long userId){
        return userRepository.findById(userId).orElseThrow(UserDoesNotExistsException::new);
    }
}
