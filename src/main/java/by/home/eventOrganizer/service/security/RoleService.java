package by.home.eventOrganizer.service.security;

import by.home.eventOrganizer.model.security.Role;

import java.util.List;

/**
 * The interface Role service.
 */
public interface RoleService {

    /**
     * Find all list of role.
     *
     * @return the list
     */
    List<Role> findAll();

    /**
     * Find by role id.
     *
     * @param id the id
     * @return the role
     */
    Role findById(Long id);

    /**
     * Find by role name.
     *
     * @param name the name
     * @return the role
     */
    Role findByName(String name);

    /**
     * Save role.
     *
     * @param role the role
     * @return the role
     */
    Role save(Role role);

    /**
     * Update role.
     *
     * @param role the role
     * @return the role
     */
    Role update(Role role);

    /**
     * Delete role.
     *
     * @param role the role
     */
    void delete(Role role);

    /**
     * Delete role by id.
     *
     * @param id the id
     */
    void deleteById(Long id);
}
