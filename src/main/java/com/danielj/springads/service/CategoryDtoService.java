package com.danielj.springads.service;

import com.danielj.springads.domain.Category;
import com.danielj.springads.dto.CategoryDto;

import java.util.List;

/**
 * CategoryDtoService interface
 *
 * @author Daniel Johansson
 */
public interface CategoryDtoService {
    List<CategoryDto> convertCategoryToCategoryDto(List<Category> category);

}
