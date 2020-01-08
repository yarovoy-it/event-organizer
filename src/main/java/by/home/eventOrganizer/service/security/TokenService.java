package by.home.eventOrganizer.service.security;

import org.springframework.security.core.Authentication;

/**
 * The interface Token service.
 */
public interface TokenService {

    /**
     * Generate new token string.
     *
     * @param authentication the authentication
     * @return the string
     */
    String generate(Authentication authentication);

    /**
     * Refresh token.
     *
     * @param token the token
     * @return the string
     */
    String refresh(String token);

    /**
     * Extract username by token.
     *
     * @param token the token
     * @return the string
     */
    String extractUsername(String token);

    /**
     * Validate token boolean.
     *
     * @param authToken the auth token
     * @return the boolean
     */
    boolean validate(String authToken);
}
