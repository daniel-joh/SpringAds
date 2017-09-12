package com.danielj.springads.domain;

import javax.persistence.*;

/**
 * Category class
 *
 * @author Daniel Johansson
 */
@Entity
public class Category {

    /**
     * Id of Category
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Name of Category
     */
    private String name;

    /* Constructors */

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    /* Getters and setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
