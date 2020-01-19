package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.dto.security.RoleDto;
import by.home.eventOrganizer.model.security.Role;
import by.home.eventOrganizer.service.security.RoleService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The type Role controller.
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final Mapper mapper;

    private final RoleService roleService;

    /**
     * Instantiates a new Role controller.
     *
     * @param mapper      the mapper
     * @param roleService the role service
     */
    public RoleController(Mapper mapper, RoleService roleService) {
        this.mapper = mapper;
        this.roleService = roleService;
    }

    /**
     * Save response entity.
     *
     * @param roleDto the role dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<RoleDto> save(@Valid @RequestBody RoleDto roleDto) {
        roleDto.setId(null);
        final RoleDto responseRoleDto = mapper.map(roleService.save(mapper.map(roleDto, Role.class)), RoleDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        roleService.deleteById(id);
    }
}
