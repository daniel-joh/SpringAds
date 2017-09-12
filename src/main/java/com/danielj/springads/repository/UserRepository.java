package com.danielj.springads.repository;

import com.danielj.springads.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interface for User repository
 *
 * @author Daniel Johansson
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Finds user by google id
     *
     * @param googleId google id of the user
     * @return User object
     */
    User findByGoogleId(String googleId);
}
