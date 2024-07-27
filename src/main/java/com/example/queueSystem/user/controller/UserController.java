package com.example.queueSystem.user.controller;

import com.example.queueSystem.user.entity.RestaurantUser;
import com.example.queueSystem.user.entity.User;
import com.example.queueSystem.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody Map<String,Object> details) {
        
        Integer phoneNumber = (int) details.get("phoneNumber");

        Optional<User> user = userRepository.findUserByPhoneNumber(phoneNumber);

        if(user.isPresent()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User exists");
        }

        User createUser = new User(phoneNumber);

        if (details.containsKey("email")) {
            createUser = new User(phoneNumber,(String) details.get("email"));
        }
        
        createUser = userRepository.save(createUser);

        if (createUser != null) {
            return ResponseEntity.ok("Created Successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
    }

    @PostMapping("/createRestaurantUser")
    public ResponseEntity<String> createRestaurantUser(@RequestBody Map<String,Object> details) {
        
        Integer phoneNumber = (int) details.get("phoneNumber");
        String email = (String) details.get("email");
        String username = (String) details.get("userName");
        String password = (String) details.get("password");

        Optional<RestaurantUser> restaurantUser = userRepository.findUserByUserName(username);

        if(restaurantUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User exists");
        }

        RestaurantUser createUser = new RestaurantUser(phoneNumber,email,username,password);
        
        createUser = userRepository.save(createUser);

        if (createUser != null) {
            return ResponseEntity.ok("Created Successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
    }

    @GetMapping("/getUser")
    public ResponseEntity<RestaurantUser> getUser(@RequestBody Map<String,Object> details) {
        String username = (String) details.get("userName");
        String password = (String) details.get("password");
        
        Optional<RestaurantUser> restaurantUser = null;

        restaurantUser = userRepository.findUserByNameAndPassword(username, password);

        if(restaurantUser.isPresent()) {
            return ResponseEntity.ok(restaurantUser.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/updateRestaurantUserDetails")
    public ResponseEntity<String> updateRestaurantUserDetails(@RequestBody Map<String,Object> details) {
        
        String username = (String) details.get("userName");
        String oldPassword = (String) details.get("oldPassword");

        Optional<RestaurantUser> userOpt = userRepository.findUserByNameAndPassword(username, oldPassword);

        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such user");
        }
    
        RestaurantUser user = userOpt.get();

        //Update basic details
        if (details.containsKey("newPassword")) {
            user.setPassword((String) details.get("newPassword"));
        }
        if (details.containsKey("phoneNumber")) {
            user.setPhoneNumber((Integer) details.get("phoneNumber"));
        }
        if (details.containsKey("email")) {
            user.setEmail((String) details.get("email"));
        }
        
        user = userRepository.save(user);

        if (user != null) {
            return ResponseEntity.ok("Update Successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
    }
}
