package by.home.eventOrganizer.controller;

import by.home.eventOrganizer.dto.security.TokenResponseDto;
import by.home.eventOrganizer.dto.security.UserDto;
import by.home.eventOrganizer.dto.security.UserRegistrationRequestDto;
import by.home.eventOrganizer.service.security.TokenService;
import by.home.eventOrganizer.service.security.UserService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Authentication controller.
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final UserService userService;

    private final TokenService tokenService;

    private final Mapper mapper;

    private final AuthenticationManager authenticationManager;

    /**
     * Instantiates a new Authentication controller.
     *
     * @param userService           the user service
     * @param tokenService          the token service
     * @param mapper                the mapper
     * @param authenticationManager the authentication manager
     */
    public AuthenticationController(UserService userService, TokenService tokenService, Mapper mapper, AuthenticationManager authenticationManager) {
        this.userService = userService;

        this.tokenService = tokenService;
        this.mapper = mapper;

        this.authenticationManager = authenticationManager;
    }

    /**
     * Authenticate user token response dto.
     *
     * @param userRegistrationRequestDto the user registration request dto
     * @return the token response dto
     */
    @PostMapping("/signIn")
    public TokenResponseDto authenticateUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userRegistrationRequestDto.getUsername(), userRegistrationRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new TokenResponseDto(tokenService.generate(authentication));
    }

    /**
     * Register user response entity.
     *
     * @param userRegistrationRequestDto the user registration request dto
     * @return the response entity
     */
    @PostMapping("/signUp")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        final UserDto userDto = mapper
                .map(userService.registration(userRegistrationRequestDto.getUsername(), userRegistrationRequestDto.getPassword(), userRegistrationRequestDto.getRoles()),
                        UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
