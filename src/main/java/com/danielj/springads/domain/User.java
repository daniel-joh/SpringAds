package com.danielj.springads.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User class
 *
 * @author Daniel Johansson
 */
@Entity
public class User {

    /**
     * Id of User
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * First name of User
     */
    private String firstName;

    /**
     * Last name of User
     */
    private String lastName;

    /**
     * Email of User
     */
    private String email;

    /**
     * Phone number of User
     */
    private String phoneNumber;

    /**
     * Google Id used by User
     */
    private String googleId;

    /**
     * List of ads created by User
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ad> ads = new ArrayList<>();

    /**
     * Address of User
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    /* Constructors */

    public User() {
    }

    public User(String firstName, String lastName, String email, String phoneNumber, String googleId,
                Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.googleId = googleId;
        this.address = address;
    }

    public User(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /* Getters and setters */

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public void addAd(Ad ad) {
        ad.setUser(this);
        this.ads.add(ad);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", googleId='" + googleId + '\'' +
                ", address=" + address +
                '}';
    }

}
