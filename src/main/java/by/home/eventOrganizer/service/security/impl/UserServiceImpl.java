package by.home.eventOrganizer.service.security.impl;

import by.home.eventOrganizer.component.LocalizedMessageSource;
import by.home.eventOrganizer.model.security.Role;
import by.home.eventOrganizer.model.security.User;
import by.home.eventOrganizer.repository.security.UserRepository;
import by.home.eventOrganizer.service.security.RoleService;
import by.home.eventOrganizer.service.security.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static by.home.eventOrganizer.component.Util.validate;

/**
 * The type User service.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final LocalizedMessageSource localizedMessageSource;

    private final RoleService roleService;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    /**
     * Instantiates a new User service.
     *
     * @param localizedMessageSource the localized message source
     * @param roleService            the role service
     * @param userRepository         the user repository
     * @param encoder                the encoder
     */
    public UserServiceImpl(LocalizedMessageSource localizedMessageSource, RoleService roleService, UserRepository userRepository, PasswordEncoder encoder) {
        this.localizedMessageSource = localizedMessageSource;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.user.notExist", new Object[]{})));
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public User save(User user) {
        validate(user.getId() != null, "error.user.notHaveId");
        validate(userRepository.existsByUsername(user.getUsername()), "error.user.notUniqueLogin");
        return saveAndFlush(user);
    }

    @Override
    public User update(User user) {
        final Long id = user.getId();
        validate(id == null, "error.user.haveId");
        final User duplicateUser = userRepository.findByUsername(user.getUsername());
        final boolean isDuplicateExists = duplicateUser != null && !Objects.equals(duplicateUser.getId(), id);
        validate(isDuplicateExists, "error.user.notUnique");
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

    @Override
    public User registration(String name, String password, Set<String> roles) {
        final User user = new User();
        user.setUsername(name);
        user.setPassword(encoder.encode(password));
        final Set<Role> userRoles = roles.stream()
                .map(roleService::findByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        user.setRoles(userRoles);
        return save(user);

    }

    private User saveAndFlush(User user) {
        user.getRoles().forEach(role -> {
            validate(role == null || role.getId() == null,
                    "error.user.roles.isNull");
            role.setName(roleService.findById(role.getId()).getName());
        });
        return userRepository.saveAndFlush(user);
    }


}
