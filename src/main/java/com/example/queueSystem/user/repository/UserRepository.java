package com.example.queueSystem.user.repository;

import com.example.queueSystem.user.entity.User;
import com.example.queueSystem.user.entity.RestaurantUser;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    /**
     * Find user by username.
     * 
     * @param username to search for.
     * @return A user matching the given username.
     */
    @Query("{ 'userName' : ?0, 'password' : ?1 }")
    Optional<RestaurantUser> findUserByNameAndPassword(String username, String password);

    @Query("{ 'userName' : ?0 }")
    Optional<RestaurantUser> findUserByUserName(String username);

    @Query("{ 'phoneNumber' : ?0 }")
    Optional<User> findUserByPhoneNumber(Integer phoneNumber);
}
