package by.home.eventOrganizer.repository.security;

import by.home.eventOrganizer.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Exists user by username boolean.
     *
     * @param username the username
     * @return the boolean
     */
    boolean existsByUsername(String username);

    /**
     * Find by username user.
     *
     * @param name the name
     * @return the user
     */
    User findByUsername(String name);
}
