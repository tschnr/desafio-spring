package com.mercadolivre.socialmeli.handler;

import com.mercadolivre.socialmeli.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserDoesNotExistsException.class)
    public ResponseEntity<Object> userDoesNotExistsException(){
        return new ResponseEntity<>("User does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotSeller.class)
    public ResponseEntity<Object> userNotSeller(){
        return new ResponseEntity<>("This user is not a seller", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyFollowSeller.class)
    public ResponseEntity<Object> userAlreadyFollowSeller(){
        return new ResponseEntity<>("You Already Follow Seller", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserFollowedIsDoesNotSeller.class)
    public ResponseEntity<Object> userFollowedIsDoesNotSeller(){
        return new ResponseEntity<>("This user is not a seller", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserIsSeller.class)
    public ResponseEntity<Object> userIsSeller(){
        return new ResponseEntity<>("This user is a seller", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDoesNotFollowSeller.class)
    public ResponseEntity<Object> userDoesNotFollowSeller(){
        return new ResponseEntity<>("User does not follow seller", HttpStatus.BAD_REQUEST);
    }


}
