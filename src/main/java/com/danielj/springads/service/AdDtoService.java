package com.danielj.springads.service;

import com.danielj.springads.domain.Ad;
import com.danielj.springads.dto.AdDto;

import java.util.List;

/**
 * AdDtoService interface
 *
 * @author Daniel Johansson
 */
public interface AdDtoService {
    List<AdDto> convertAdListToAdDtoList(List<Ad> ad);

    AdDto convertAdToAdDto(Ad ad);

    Ad convertAdDtoToAd(AdDto adDto);
}
