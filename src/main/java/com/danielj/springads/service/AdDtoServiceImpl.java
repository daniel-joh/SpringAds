package com.danielj.springads.service;

import com.danielj.springads.domain.Ad;
import com.danielj.springads.domain.AdType;
import com.danielj.springads.domain.Category;
import com.danielj.springads.dto.AdDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation class of AdDtoService
 *
 * @author Daniel Johansson
 */
@Service
public class AdDtoServiceImpl implements AdDtoService {
    private CategoryService categoryService;

    public AdDtoServiceImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Converts List of Ad´s to List of AdDto´s
     *
     * @param adList List of Ad´s to be converted
     * @return List of AdDto´s
     */
    @Override
    public List<AdDto> convertAdListToAdDtoList(List<Ad> adList) {
        List<AdDto> adDtoList = new ArrayList<>();

        for (Ad ad : adList) {
            AdDto adDto = convertAdToAdDto(ad);
            adDtoList.add(adDto);
        }
        return adDtoList;
    }

    /**
     * Converts Ad to AdDto
     *
     * @param ad Ad to be converted
     * @return AdDto
     */
    @Override
    public AdDto convertAdToAdDto(Ad ad) {
        AdDto adDto = new AdDto();

        adDto.setId(ad.getId());
        adDto.setActive(ad.isActive());
        adDto.setType(ad.getType().toString());
        adDto.setHeadline(ad.getHeadline());
        adDto.setDescription(ad.getDescription());
        adDto.setPrice(ad.getPrice());
        adDto.setDateCreated(ad.getDateCreated().toString());
        adDto.setDateEnding(ad.getDateEnding().toString());
        adDto.setCategoryName(ad.getCategory().getName());
        adDto.setImage(ad.getImage());
        adDto.setFirstName(ad.getUser().getFirstName());
        adDto.setLastName(ad.getUser().getLastName());
        adDto.setEmail(ad.getUser().getEmail());
        adDto.setPhoneNumber(ad.getUser().getPhoneNumber());
        adDto.setStreetAddress(ad.getUser().getAddress().getStreetAddress());
        adDto.setCity(ad.getUser().getAddress().getCity());
        adDto.setZipcode(ad.getUser().getAddress().getZipcode());
        adDto.setState(ad.getUser().getAddress().getState());

        return adDto;
    }

    /**
     * Converts AdDto to Ad
     *
     * @param adDto AdDto to be converted
     * @return Ad
     */
    @Override
    public Ad convertAdDtoToAd(AdDto adDto) {
        Ad ad = new Ad();

        ad.setHeadline(adDto.getHeadline());
        ad.setDescription(adDto.getDescription());
        ad.setPrice(adDto.getPrice());

        //Finds the correct category
        Category category = categoryService.findByName(adDto.getCategoryName());

        if (category != null) {
            ad.setCategory(category);
        }

        ad.setType(AdType.valueOf(adDto.getType().toUpperCase()));
        ad.setImage(adDto.getImage());

        return ad;
    }
}
