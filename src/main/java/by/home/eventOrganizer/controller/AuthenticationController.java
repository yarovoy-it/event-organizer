package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.dto.security.TokenResponseDto;
import by.home.eventOrganizer.dto.security.UserRegistrationRequestDto;
import by.home.eventOrganizer.model.security.Role;
import by.home.eventOrganizer.model.security.User;
import by.home.eventOrganizer.service.security.RoleService;
import by.home.eventOrganizer.service.security.TokenService;
import by.home.eventOrganizer.service.security.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private final UserService userService;

    private final RoleService roleService;

    private final TokenService tokenService;

    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(UserService userService, RoleService roleService, TokenService tokenService, PasswordEncoder encoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.roleService = roleService;
        this.tokenService = tokenService;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signIn")
    public TokenResponseDto authenticateUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userRegistrationRequestDto.getUsername(), userRegistrationRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new TokenResponseDto(tokenService.generate(authentication));
    }

    @PostMapping("/signUp")
    public User registerUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        final User user = new User();
        user.setUsername(userRegistrationRequestDto.getUsername());
        user.setPassword(encoder.encode(userRegistrationRequestDto.getPassword()));
        final Set<Role> roles = userRegistrationRequestDto.getRoles().stream()
                .map(name -> roleService.findByName(name))
                .filter(obj -> Objects.nonNull(obj))
                .collect(Collectors.toSet());
        user.setRoles(roles);
        return userService.save(user);
    }
}
