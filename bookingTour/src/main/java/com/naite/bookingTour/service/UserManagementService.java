package com.naite.bookingTour.service;

import java.util.List;  
import java.util.Optional;

import com.naite.bookingTour.model.User;  

public interface UserService {  
  List<User> getAllUser();  

  void saveUser(User user);  

  void deleteUser(Long id);  

  Optional<User> findUserById(Long id);  
}