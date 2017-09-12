package com.danielj.springads.service;

import com.danielj.springads.domain.Ad;

import java.util.List;

/**
 * AdService interface
 *
 * @author Daniel Johansson
 */
public interface AdService {
    List<Ad> getAds(String query, String categoryName, String type, String state);

    List<Ad> getAllActiveAds();

    List<Ad> getAllAdsByUserId(Long id);

    Ad getAd(Long id);

    void saveAd(Ad ad);

    void updateAd(Ad originalAd, Ad editedAd);

    void deleteAd(Long id);

}
