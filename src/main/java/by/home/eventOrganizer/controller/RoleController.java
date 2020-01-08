package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.dto.security.RoleDto;
import by.home.eventOrganizer.model.security.Role;
import by.home.eventOrganizer.service.security.RoleService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final Mapper mapper;

    private final RoleService roleService;

    public RoleController(Mapper mapper, RoleService roleService) {
        this.mapper = mapper;
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDto> save(@Valid @RequestBody RoleDto roleDto) {
        roleDto.setId(null);
        final RoleDto responseRoleDto = mapper.map(roleService.save(mapper.map(roleDto, Role.class)), RoleDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        roleService.deleteById(id);
    }
}
