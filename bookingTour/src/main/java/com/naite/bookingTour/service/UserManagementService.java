package com.naite.bookingTour.service;

import java.util.List;
import java.util.Optional;

import com.naite.bookingTour.model.User;  

public interface UserManagementService {
  List<User> findAllUsers();

  void saveUser(User user);

  void deleteUser(Long id);

  Optional<User> findUserByEmail(String email);

  Optional<User> findUserById(Long id);

  Optional<User> findByUsername(String username);
}