package com.danielj.springads.service;

import com.danielj.springads.domain.Category;

import java.util.List;

/**
 * CategoryService interface
 *
 * @author Daniel Johansson
 */
public interface CategoryService {

    void saveCategory(Category category);

    List<Category> getCategories();

    Category findByName(String name);
}
