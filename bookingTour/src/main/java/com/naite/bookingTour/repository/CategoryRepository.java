package com.naite.bookingTour.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naite.bookingTour.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findAll();
	Category findByName(String name);

	@Override
	Optional<Category> findById(Long id);

	Set<Category> findByIdIn(List<Long> categoryIds);
}