package com.example.prasath_spring.controller;

import com.example.prasath_spring.exception.UserNotFoundException;
import com.example.prasath_spring.model.User;
import com.example.prasath_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("${Frontend_URL}")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);

    }
    @GetMapping("/users")
    @Query("SELECT u FROM User u ORDER BY u.id ASC")
    List<User> getAllUsers(){

        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }
    @PutMapping ("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername((newUser.getUsername()));
                    user.setName((newUser.getName()));
                    user.setEmail((newUser.getEmail()));
                    return userRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById((id));
        return "User with id :"+id+"- has been deleted Sucess..";
    }
}
