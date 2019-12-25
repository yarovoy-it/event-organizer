package by.home.eventOrganizer.repository.security;

import by.home.eventOrganizer.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByUsername(String username);

    User findByUsername(String name);
}
