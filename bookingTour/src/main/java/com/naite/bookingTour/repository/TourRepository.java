package com.naite.bookingTour.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naite.bookingTour.model.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findAll();

    Optional<Tour> findById(Long id);

//    @Query("SELECT t FROM Tour t JOIN t.dateTours dt WHERE dt.startDate >= :startDate AND dt.endDate <= :endDate")
//    List<Tour> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}