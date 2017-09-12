package com.danielj.springads.restcontroller;

import com.danielj.springads.domain.User;
import com.danielj.springads.dto.UserDto;
import com.danielj.springads.service.UserDtoService;
import com.danielj.springads.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller class for users resource
 *
 * @author Daniel Johansson
 */
@RestController
public class UserController {
    private UserService userService;
    private UserDtoService userDtoService;

    public UserController(UserService userService, UserDtoService userDtoService) {
        this.userService = userService;
        this.userDtoService = userDtoService;
    }

    /**
     * Gets a user
     *
     * @param googleId google id for user
     * @return UserDto
     */
    @GetMapping("/users")
    public UserDto getUser(@RequestParam(value = "googleId") String googleId) {
        User user = userService.getUser(googleId);                    //Finds the correct User

        if (user != null)
            return userDtoService.convertUserToUserDto(user);       //Converts User to UserDto and returns it
        else
            return null;
    }

    /**
     * Creates a user
     *
     * @param userDto UserDto
     * @return UserDto of created user
     */
    @PostMapping("/users/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto newUserDto;
        try {
            User newUser = userDtoService.convertUserDtoToUser(userDto); //Converts from UserDto to User
            userService.saveUser(newUser);                            //Saves the new user to db

            // Converts from User to UserDto (for return)
            newUserDto = userDtoService.convertUserToUserDto(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserDto>(newUserDto, HttpStatus.CREATED);
    }

    /**
     * Updates an user
     *
     * @param id      id of user to be updated
     * @param userDto UserDto
     */
    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable("id") long id, @RequestBody UserDto userDto) {
        User updatedUser;
        try {
            User originalUser = userService.getUser(id);                       //Finds the correct User
            updatedUser = userDtoService.convertUserDtoToUser(userDto);   //Converts from UserDto to User

            //Updates User object with new values and saves it
            userService.updateUser(originalUser, updatedUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a user
     *
     * @param id id of user to be deleted
     */
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
