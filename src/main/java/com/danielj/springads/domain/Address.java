package com.danielj.springads.domain;

import javax.persistence.*;

/**
 * Address class
 *
 * @author Daniel Johansson
 */
@Entity
public class Address {

    /**
     * Id of Address
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Street address
     */
    private String streetAddress;

    /**
     * City
     */
    private String city;

    /**
     * Zip code
     */
    private String zipcode;

    /**
     * State
     */
    private String state;

    /* Constructors */

    public Address() {
    }

    public Address(String streetAddress, String city, String zipcode, String state) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;
    }

    /* Getters and setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

}
