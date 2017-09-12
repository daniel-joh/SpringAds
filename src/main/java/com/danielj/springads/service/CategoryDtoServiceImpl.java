package com.danielj.springads.service;

import com.danielj.springads.domain.Category;
import com.danielj.springads.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation class of CategoryDtoService
 *
 * @author Daniel Johansson
 */
@Service
public class CategoryDtoServiceImpl implements CategoryDtoService {

    public CategoryDtoServiceImpl() {
    }

    /**
     * Converts List of categories to List of CategoryDto´s
     *
     * @param categoryList List of categories
     * @return List of CategoryDto´s
     */
    @Override
    public List<CategoryDto> convertCategoryToCategoryDto(List<Category> categoryList) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for (Category category : categoryList) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category.getName());
            categoryDtoList.add(categoryDto);
        }

        return categoryDtoList;
    }

}
