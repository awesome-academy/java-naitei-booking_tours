package com.naite.bookingTour.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naite.bookingTour.model.Booking;
import com.naite.bookingTour.model.User;
import com.naite.bookingTour.model.Tour;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    
    List<Booking> findByTour(Tour tour);
    
    List<Booking> findByStatus(String status);
    
    List<Booking> findByUserAndStatus(User user, String status);
    
    Optional<Booking> findByIdAndStatus(Long id, String status);
}