package com.example.queueSystem.user;

import com.example.queueSystem.user.entity.User;
import com.example.queueSystem.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDataLoader {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void run() {
        try {
            userRepository.deleteAll();
            userRepository.save(new User(93293819, "cydnietest@gmail.com"));
            userRepository.save(new User(82371231, "cydnietest2@gmail.com"));
        } catch (Exception e) {
            // Handle any exceptions that may occur during data loading
            // Log the error or perform any necessary cleanup
            e.printStackTrace();
        }
    }
}
