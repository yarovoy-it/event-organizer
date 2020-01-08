package by.home.eventOrganizer.repository.security;

import by.home.eventOrganizer.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Role repository.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Exists role by name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    boolean existsByName(String name);

    /**
     * Find by name role.
     *
     * @param name the name
     * @return the role
     */
    Role findByName(String name);
}
