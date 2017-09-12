package com.danielj.springads.restcontroller;

import com.danielj.springads.domain.Category;
import com.danielj.springads.dto.CategoryDto;
import com.danielj.springads.service.CategoryDtoService;
import com.danielj.springads.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller class for categories resource
 *
 * @author Daniel Johansson
 */
@RestController
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryDtoService categoryDtoService;

    public CategoryController(CategoryService categoryService, CategoryDtoService categoryDtoService) {
        this.categoryService = categoryService;
        this.categoryDtoService = categoryDtoService;
    }

    /**
     * Gets a List of all categories
     *
     * @return List of Dto categories
     */
    @GetMapping("/categories/")
    public List<CategoryDto> getCategories() {
        //Gets all categories
        List<Category> categoryList = categoryService.getCategories();

        //Converts categories to category Dto´s
        List<CategoryDto> categoryDtoList = categoryDtoService.convertCategoryToCategoryDto(categoryList);

        return categoryDtoList;
    }

}
