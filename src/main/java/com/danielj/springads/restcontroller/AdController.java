package com.danielj.springads.restcontroller;

import java.util.List;

import com.danielj.springads.domain.User;
import com.danielj.springads.dto.AdDto;
import com.danielj.springads.service.AdDtoService;
import com.danielj.springads.service.AdService;
import com.danielj.springads.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.danielj.springads.domain.Ad;

/**
 * Rest controller class for ads resource
 *
 * @author Daniel Johansson
 */
@RestController
public class AdController {
    private final AdService adService;
    private final AdDtoService adDtoService;
    private final UserService userService;

    public AdController(AdService adService, AdDtoService adDtoService, UserService userService) {
        this.adService = adService;
        this.adDtoService = adDtoService;
        this.userService = userService;
    }

    /**
     * Gets all ads for an user
     *
     * @param id id of user
     * @return List of AdDto´s
     */
    @GetMapping("/users/{id}/ads/")
    public List<AdDto> getAllAdsByUserId(@PathVariable("id") long id) {
        //Gets all ads for a specific user
        List<Ad> listAds = adService.getAllAdsByUserId(id);

        //Converts List of Ad´s to List of AdDto´s
        List<AdDto> listDto = adDtoService.convertAdListToAdDtoList(listAds);

        return listDto;
    }

    /**
     * Gets all active ads
     *
     * @return List of AdDto´s
     */
    @GetMapping("/ads/")
    public List<AdDto> getAllAds() {
        //Gets all active ads
        List<Ad> listAds = adService.getAllActiveAds();

        //Converts List of Ad´s to List of AdDto´s
        List<AdDto> listDto = adDtoService.convertAdListToAdDtoList(listAds);

        return listDto;
    }

    /**
     * Gets a List of AdDto´s matching searchString, name of category, state and type
     *
     * @param searchString searchString that user searches for
     * @param category     name of category
     * @param state        state
     * @param type         Adtype
     * @return List of AdDto´s
     */
    @GetMapping("/search")
    public List<AdDto> getAds(@RequestParam(value = "searchString") String searchString,
                              @RequestParam(value = "category") String category,
                              @RequestParam(value = "state") String state,
                              @RequestParam(value = "type") String type) {
        //Gets a List of ads matching searchString, name of category, state and type
        List<Ad> listAds = adService.getAds(searchString, category, type, state);

        //Converts List of Ad´s to List of AdDto´s
        List<AdDto> listDto = adDtoService.convertAdListToAdDtoList(listAds);

        return listDto;
    }

    /**
     * Creates an Ad
     *
     * @param id    id of User
     * @param adDto AdDto
     * @return
     */
    @PostMapping("/users/{id}/ads/")
    public ResponseEntity<AdDto> createAd(@PathVariable("id") long id, @RequestBody AdDto adDto) {
        Ad newAd;
        AdDto adDtoResponse;

        try {
            newAd = adDtoService.convertAdDtoToAd(adDto);            //Converts from AdDto to Ad
            User user = userService.getUser(id);                  //Gets the User that owns the Ad
            user.addAd(newAd);

            adService.saveAd(newAd);                            //Saves the new Ad to db

            adDtoResponse = adDtoService.convertAdToAdDto(newAd);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<AdDto>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<AdDto>(adDtoResponse, HttpStatus.CREATED);

    }

    /**
     * Updates an ad
     *
     * @param id id of ad to update
     * @param ad ad to update
     */
    @PutMapping("/ads/{id}")
    public void updateAd(@PathVariable("id") Long id, @RequestBody AdDto ad) {
        Ad updatedAd;

        try {
            Ad originalAd = adService.getAd(id);                       //Finds the correct Ad
            updatedAd = adDtoService.convertAdDtoToAd(ad);            //Converts from AdDto to Ad

            adService.updateAd(originalAd, updatedAd);  //Updates Ad object with new values and saves it

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an ad
     *
     * @param id id of ad to delete
     */
    @DeleteMapping("/ads/{id}")
    public void deleteAd(@PathVariable("id") long id) {
        try {
            adService.deleteAd(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

