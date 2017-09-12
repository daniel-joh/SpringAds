package com.danielj.springads.repository;

import com.danielj.springads.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Category repository interface
 *
 * @author Daniel Johansson
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
    /**
     * Finds a category by name
     *
     * @param name name of category
     * @return Category object
     */
    Category findByName(String name);
}
