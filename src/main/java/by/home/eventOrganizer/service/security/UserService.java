package by.home.eventOrganizer.service.security;

import by.home.eventOrganizer.model.security.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByName(String name);

    User save(User user);

    User update(User user);

    void delete(User user);

    void deleteById(Long id);
}
