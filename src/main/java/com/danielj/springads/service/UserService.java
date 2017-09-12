package com.danielj.springads.service;

import com.danielj.springads.domain.User;

/**
 * UserService interface
 *
 * @author Daniel Johansson
 */
public interface UserService {
    User getUser(String googleId);

    User getUser(long id);

    void saveUser(User user);

    void updateUser(User oldUser, User editedUser);

    void deleteUser(long id);
}
