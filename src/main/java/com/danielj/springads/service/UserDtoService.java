package com.danielj.springads.service;

import com.danielj.springads.domain.User;
import com.danielj.springads.dto.UserDto;

/**
 * UserDtoService interface
 *
 * @author Daniel Johansson
 */
public interface UserDtoService {
    UserDto convertUserToUserDto(User user);

    User convertUserDtoToUser(UserDto userDto);
}
