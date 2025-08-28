package com.example.prasath_spring.exception;

public class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException(Long id){
        super("Could not found the User id :"+id);
    }
}
