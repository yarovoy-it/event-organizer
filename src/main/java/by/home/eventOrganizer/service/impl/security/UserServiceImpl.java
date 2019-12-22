package by.home.eventOrganizer.service.impl.security;

import by.home.eventOrganizer.model.security.User;
import by.home.eventOrganizer.repository.security.UserRepository;
import by.home.eventOrganizer.service.security.RoleService;
import by.home.eventOrganizer.service.security.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final RoleService roleService;

    private final UserRepository userRepository;

    public UserServiceImpl(RoleService roleService, UserRepository userRepository) {
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("error.user.notExist"));
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User save(User user) {
        validate(user.getId() != null, "error.user.notHaveId");
        validate(userRepository.existsByName(user.getLogin()), "error.user.notUniqueLogin");
        return saveAndFlush(user);
    }

    @Override
    public User update(User user) {
        final Long id = user.getId();
        validate(id == null, "error.user.haveId");
        final User duplicateUser = userRepository.findByName(user.getLogin());
        final boolean isDuplicateExists = duplicateUser != null && !Objects.equals(duplicateUser.getId(), id);
        validate(isDuplicateExists, "error.user.name.notUnique");
        findById(id);
        return saveAndFlush(user);
    }

    @Override
    public void delete(User user) {
        validate(user == null, "error.user.haveId");
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private User saveAndFlush(User user) {
        user.getRoles().forEach(role -> {
            validate(role == null || role.getId() == null,
                    "error.user.roles.isNull");
            role.setName(roleService.findById(role.getId()).getName());
        });
        return userRepository.saveAndFlush(user);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
