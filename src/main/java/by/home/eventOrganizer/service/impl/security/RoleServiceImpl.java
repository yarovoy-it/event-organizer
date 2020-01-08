package by.home.eventOrganizer.service.impl.security;

import by.home.eventOrganizer.component.LocalizedMessageSource;
import by.home.eventOrganizer.model.security.Role;
import by.home.eventOrganizer.repository.security.RoleRepository;
import by.home.eventOrganizer.service.security.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final LocalizedMessageSource localizedMessageSource;

    private final RoleRepository roleRepository;

    public RoleServiceImpl(LocalizedMessageSource localizedMessageSource, RoleRepository roleRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.role.notExist", new Object[]{})));
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role save(Role role) {
        validate(role.getId() != null, "error.user.notHaveId");
        validate(roleRepository.existsByName(role.getName()), "error.user.notUnique");
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public Role update(Role role) {
        final Long id = role.getId();
        validate(id == null, "error.role.haveId");
        final Role duplicateRole = roleRepository.findByName(role.getName());
        final boolean isDuplicateExists = duplicateRole != null && !Objects.equals(duplicateRole.getId(), id);
        validate(isDuplicateExists, "error.role.name.notUnique");
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public void delete(Role role) {
        validate(role == null, "error.role.haveId");
        roleRepository.delete(role);
    }

    @Override
    public void deleteById(Long id) {
        validate(findById(id)==null, "error.role.notFind");
        roleRepository.deleteById(id);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(localizedMessageSource.getMessage(errorMessage, new Object[]{}));
        }
    }
}
