package com.naite.bookingTour.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naite.bookingTour.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    // Custom method to authenticate the user
    default Optional<User> authenticate(String username, String password) {
        Optional<User> userOptional = findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                return userOptional; // Authentication successful
            }
        }

        return Optional.empty(); // Authentication failed
    }

    Optional<User> findUserById(Long id);

}