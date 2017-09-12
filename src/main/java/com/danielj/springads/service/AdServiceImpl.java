package com.danielj.springads.service;

import com.danielj.springads.domain.Ad;
import com.danielj.springads.domain.AdType;
import com.danielj.springads.repository.AdRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Implementation class of AdService
 *
 * @author Daniel Johansson
 */
@Service
public class AdServiceImpl implements AdService {
    private AdRepository adRepository;

    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    /**
     * Gets all active ads, sorted by dateCreated
     *
     * @return List of ads
     */
    @Override
    public List<Ad> getAllActiveAds() {
        return adRepository.findByActiveOrderByDateCreatedDesc(true);
    }

    /**
     * Gets all ads belonging to a specific user
     *
     * @param id id of User
     * @return List of ads
     */
    @Override
    public List<Ad> getAllAdsByUserId(Long id) {
        return adRepository.findByUser_Id(id);
    }

    /**
     * Gets a specific Ad
     *
     * @param id id of Ad
     * @return Ad
     */
    @Override
    public Ad getAd(Long id) {
        return adRepository.findOne(id);
    }

    /**
     * Gets a List of ads matching searchString, name of category, state and type
     *
     * @param searchString searchString that user searches for
     * @param categoryName name of category
     * @param type         Adtype
     * @param state        state
     * @return List of ads
     */
    @Override
    public List<Ad> getAds(String searchString, String categoryName, String type, String state) {
        List<Ad> ads;
        AdType adType = AdType.valueOf(type);

        //If category isn´t "All" - a specific category has been chosen
        if (!categoryName.equals("All")) {
            //If state isn´t "All" - a specific state has been chosen
            if (!state.equals("All")) {
                //Gets all active ads matching category, type, state and searchString
                ads = adRepository.findAds(categoryName, adType, state, searchString);
            }
            //If state is "All"
            else {
                //Gets all active ads matching category, type and searchString
                ads = adRepository.findAds(categoryName, adType, searchString);
            }
        }
        //If category is "All"
        else {
            //If state isn´t "All" - a specific state has been chosen
            if (!state.equals("All")) {
                //Gets all active ads matching type, state and searchString
                ads = adRepository.findAds(adType, state, searchString);
            }

            //If state is "All"
            else {
                //Gets all active ads matching type and searchString
                ads = adRepository.findAds(adType, searchString);
            }
        }
        return ads;
    }

    /**
     * Saves an Ad
     *
     * @param ad Ad to be saved
     */
    @Override
    public void saveAd(Ad ad) {
        Date dateCreated = new Date();
        ad.setDateCreated(dateCreated);

        //Date ending=Date created plus 1 month
        Date dateEnding = DateUtils.addMonths(dateCreated, 1);
        ad.setDateEnding(dateEnding);
        ad.setActive(true);

        adRepository.save(ad);
    }

    /**
     * Updates the original Ad with the new values from the edited Ad, and stores it
     *
     * @param originalAd original Ad
     * @param editedAd   Ad with values edited by the user
     */
    @Override
    public void updateAd(Ad originalAd, Ad editedAd) {
        originalAd.setHeadline(editedAd.getHeadline());
        originalAd.setDescription(editedAd.getDescription());
        originalAd.setCategory(editedAd.getCategory());

        //Sets image if it isn´t null
        if (editedAd.getImage() != null)
            originalAd.setImage(editedAd.getImage());

        originalAd.setPrice(editedAd.getPrice());
        originalAd.setType(editedAd.getType());

        adRepository.save(originalAd);
    }

    /**
     * Deletes a specific Ad
     *
     * @param id id of Ad to be deleted
     */
    @Override
    public void deleteAd(Long id) {
        adRepository.delete(id);
    }
}
