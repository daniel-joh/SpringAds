package com.danielj.springads.domain;

import java.util.Date;
import javax.persistence.*;

/**
 * Ad class
 *
 * @author Daniel Johansson
 */
@Entity
public class Ad {

    /**
     * Id of Ad
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Headline of Ad
     */
    private String headline;

    /**
     * Description of Ad
     */
    @Lob
    private String description;

    /**
     * Price for the Ad
     */
    private long price;

    /**
     * Boolean for Ad status (active or not active)
     */
    private boolean active;

    /**
     * Type of Ad (BUY or SELL)
     */
    @Enumerated(EnumType.STRING)
    private AdType type;

    /**
     * Date when Ad was created
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    /**
     * Date when Ad is ending
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnding;

    /**
     * Category of Ad
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Category category;

    /**
     * User associated to the Ad
     */
    @ManyToOne
    private User user;

    /**
     * Image for the Ad
     */
    @Lob
    private byte[] image;

    /* Constructors */

    public Ad() {
    }

    public Ad(String headline, String description, long price, AdType type, Date dateCreated,
              Date dateEnding, Category category, User user, byte[] image, boolean active) {
        this.headline = headline;
        this.description = description;
        this.price = price;
        this.type = type;
        this.dateCreated = dateCreated;
        this.dateEnding = dateEnding;
        this.category = category;
        this.user = user;
        this.image = image;
        this.active = active;
    }

    /* Getters and setters */

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdType getType() {
        return type;
    }

    public void setType(AdType type) {
        this.type = type;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateEnding() {
        return dateEnding;
    }

    public void setDateEnding(Date dateEnding) {
        this.dateEnding = dateEnding;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", headline='" + headline + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", active=" + active +
                ", type=" + type +
                ", dateCreated=" + dateCreated +
                ", dateEnding=" + dateEnding +
                ", category=" + category.getName() +
                ", user=" + user +
                '}';
    }

}
