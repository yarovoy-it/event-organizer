package by.home.eventOrganizer.service.security;

import by.home.eventOrganizer.model.security.User;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Find all list of user.
     *
     * @return the list
     */
    List<User> findAll();

    /**
     * Find user by id .
     *
     * @param id the id
     * @return the user
     */
    User findById(Long id);

    /**
     * Find by  user name.
     *
     * @param name the name
     * @return the user
     */
    User findByName(String name);

    /**
     * Save user.
     *
     * @param user the user
     * @return the user
     */
    User save(User user);

    /**
     * Update user.
     *
     * @param user the user
     * @return the user
     */
    User update(User user);

    /**
     * Delete user.
     *
     * @param user the user
     */
    void delete(User user);

    /**
     * Delete user by id.
     *
     * @param id the id
     */
    void deleteById(Long id);
}
