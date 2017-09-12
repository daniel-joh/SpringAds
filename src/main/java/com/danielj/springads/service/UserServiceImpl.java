package com.danielj.springads.service;

import com.danielj.springads.domain.User;
import com.danielj.springads.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation class of UserService
 *
 * @author Daniel Johansson
 */
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Gets a specific user by google id
     * @param googleId google id of user to be retrieved
     * @return User
     */
    @Override
    public User getUser(String googleId) {
        return userRepository.findByGoogleId(googleId);
    }

    /**
     * Gets a specific user by id
     * @param id id of user to be retrieved
     * @return User
     */
    @Override
    public User getUser(long id) {
        return userRepository.findOne(id);
    }

    /**
     * Updates oldUser with values from editedUser, then saves it
     * @param oldUser original User
     * @param editedUser edited User
     */
    @Override
    public void updateUser(User oldUser, User editedUser) {
        oldUser.setFirstName(editedUser.getFirstName());
        oldUser.setLastName(editedUser.getLastName());
        oldUser.setPhoneNumber(editedUser.getPhoneNumber());
        oldUser.getAddress().setStreetAddress(editedUser.getAddress().getStreetAddress());
        oldUser.getAddress().setZipcode(editedUser.getAddress().getZipcode());
        oldUser.getAddress().setCity(editedUser.getAddress().getCity());
        oldUser.getAddress().setState(editedUser.getAddress().getState());

        saveUser(oldUser);
    }

    /**
     * Saves user to repository
     * @param user user to be saved
     */
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * Deletes specific user
     * @param id id of user to be deleted
     */
    @Override
    public void deleteUser(long id) {
        userRepository.delete(id);
    }
}
