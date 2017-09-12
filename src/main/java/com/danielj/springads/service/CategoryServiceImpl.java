package com.danielj.springads.service;

import com.danielj.springads.domain.Category;
import com.danielj.springads.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation class of CategoryService
 *
 * @author Daniel Johansson
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Saves a category
     * @param category category to be saved
     */
    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    /**
     * Gets all categories
     *
     * @return List of categories
     */
    @Override
    public List<Category> getCategories() {
        List<Category> categoriesList = new ArrayList<>();

        categoriesList = (List<Category>) categoryRepository.findAll();
        return categoriesList;
    }

    /**
     * Gets a specific category
     *
     * @param name name of category
     * @return Category
     */
    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
