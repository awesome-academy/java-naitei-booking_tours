package com.naite.bookingTour.service;

import com.naite.bookingTour.model.Category;
import com.naite.bookingTour.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Set<Category> findByIdIn(List<Long> categoryIds) {
        return categoryRepository.findByIdIn(categoryIds);
    }

    public Category findByName(String name){
        return categoryRepository.findByName(name);
    }
}
