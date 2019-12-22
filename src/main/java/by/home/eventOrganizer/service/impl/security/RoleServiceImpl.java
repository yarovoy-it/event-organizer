package by.home.eventOrganizer.service.impl.security;

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

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("error.role.notExist"));
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role save(Role role) {
        validate(role.getId() != null, "error.user.notHaveId");
        validate(roleRepository.existsByName(role.getName()), "error.user.notUniqueLogin");
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
    public void delete(Role user) {
        validate(user == null, "error.user.haveId");
        roleRepository.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        roleRepository.deleteById(id);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
