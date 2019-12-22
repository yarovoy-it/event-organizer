package by.home.eventOrganizer.repository.security;

import by.home.eventOrganizer.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByName(String name);

    Role findByName(String name);
}
