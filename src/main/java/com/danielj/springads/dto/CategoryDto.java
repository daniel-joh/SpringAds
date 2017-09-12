package com.danielj.springads.dto;

/**
 * DTO class for Category object
 *
 * @author Daniel Johansson
 */
public class CategoryDto {
    private String name;

    /* Constructors */

    public CategoryDto(){}

    public CategoryDto(String name) {
        this.name = name;
    }

    /* Getters and setters */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
