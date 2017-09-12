package com.danielj.springads.repository;

import com.danielj.springads.domain.AdType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.danielj.springads.domain.Ad;

import java.util.List;

/**
 * Ad repository interface
 *
 * @author Daniel Johansson
 */
public interface AdRepository extends CrudRepository<Ad, Long> {
    /**
     * Finds all active ads, order by dateCreated descending
     *
     * @param active status of Ad
     * @return List of ads
     */
    List<Ad> findByActiveOrderByDateCreatedDesc(boolean active);

    /**
     * Finds all ads, order by dateCreated descending
     *
     * @return List of ads
     */
    List<Ad> findByOrderByDateCreatedDesc();

    /**
     * Finds all ads by User id
     *
     * @param id of User
     * @return List of ads
     */
    List<Ad> findByUser_Id(Long id);

    /**
     * Finds all active ads by category name, adtype, state and searchString. Order by dateCreated, descending
     *
     * @param name         category name
     * @param type         type of Ad
     * @param state        User state
     * @param searchString string that user searches for
     * @return List of ads
     */
    @Query("select a from Ad a where a.active=true and a.category.name=?1 and a.type=?2 and a.user.address.state=?3 and (UPPER(a.headline) like %?4% or UPPER(a.description) like %?4%) order by a.dateCreated desc")
    List<Ad> findAds(String name, AdType type, String state, String searchString);

    /**
     * Finds all active ads by category name, adtype and searchString. Order by dateCreated, descending
     *
     * @param name         category name
     * @param type         type of Ad
     * @param searchString string that user searches for
     * @return List of ads
     */
    @Query("select a from Ad a where a.active=true and a.category.name=?1 and a.type=?2 and (UPPER(a.headline) like %?3% or UPPER(a.description) like %?3%) order by a.dateCreated desc")
    List<Ad> findAds(String name, AdType type, String searchString);

    /**
     * Finds all active ads by adtype, state and searchString. Order by dateCreated, descending
     *
     * @param type         type of Ad
     * @param state        User state
     * @param searchString string that user searches for
     * @return List of ads
     */
    @Query("select a from Ad a where a.active=true and a.type=?1 and a.user.address.state=?2 and (UPPER(a.headline) like %?3% or UPPER(a.description) like %?3%) order by a.dateCreated desc")
    List<Ad> findAds(AdType type, String state, String searchString);

    /**
     * Finds all active ads by adtype and searchString. Order by dateCreated, descending
     *
     * @param type         type of Ad
     * @param searchString string that user searches for
     * @return List of ads
     */
    @Query("select a from Ad a where a.active=true and a.type=?1 and (UPPER(a.headline) like %?2% or UPPER(a.description) like %?2%) order by a.dateCreated desc")
    List<Ad> findAds(AdType type, String searchString);

}
