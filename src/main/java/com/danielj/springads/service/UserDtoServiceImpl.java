package com.danielj.springads.service;

import com.danielj.springads.domain.Address;
import com.danielj.springads.domain.User;
import com.danielj.springads.dto.UserDto;
import org.springframework.stereotype.Service;

/**
 * Implementation class of UserDtoService
 *
 * @author Daniel Johansson
 */
@Service
public class UserDtoServiceImpl implements UserDtoService {

    public UserDtoServiceImpl() {
    }

    /**
     * Converts User to UserDto
     *
     * @param user User to be converted
     * @return Converted UserDto
     */
    @Override
    public UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setGoogleId(user.getGoogleId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setStreetAddress(user.getAddress().getStreetAddress());
        userDto.setCity(user.getAddress().getCity());
        userDto.setZipcode(user.getAddress().getZipcode());
        userDto.setState(user.getAddress().getState());

        return userDto;
    }

    /**
     * Converts UserDto to User
     *
     * @param userDto UserDto to be converted
     * @return Converted User
     */
    @Override
    public User convertUserDtoToUser(UserDto userDto) {
        User user = new User();

        user.setFirstName((userDto.getFirstName()));
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setGoogleId(userDto.getGoogleId());

        Address address = new Address();
        address.setStreetAddress(userDto.getStreetAddress());
        address.setZipcode(userDto.getZipcode());
        address.setCity(userDto.getCity());
        address.setState(userDto.getState());

        user.setAddress(address);

        return user;
    }
}
