package by.home.eventOrganizer.dto.security;

/**
 * The type Token response dto.
 */
public class TokenResponseDto {

    private String token;
    private String type = "Bearer";

    /**
     * Instantiates a new Token response dto.
     *
     * @param accessToken the access token
     */
    public TokenResponseDto(String accessToken) {
        this.token = accessToken;
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }
}
