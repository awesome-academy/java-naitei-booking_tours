package com.naite.bookingTour.service;

import com.naite.bookingTour.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {
    Category save(Category category);

    void deleteById(Long id) ;

    List<Category> findAll() ;

    Optional<Category> findById(Long id);

    Set<Category> findByIdIn(List<Long> categoryIds);

    Category findByName(String name);
}
