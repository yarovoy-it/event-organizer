package by.home.eventOrganizer.dto.security;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserRegistrationRequestDto {

    @NotNull(message = "{user.username.notNull}")
    @NotEmpty(message = "{user.username.notEmpty}")
    @Size(min = 3, max = 50, message = "{user.username.size}")
    private String username;

    @NotNull(message = "{user.password.notNull}")
    @NotEmpty(message = "{user.password.notEmpty}")
    @Size(min = 3, max = 100, message = "{user.password.size}")
    private String password;

    @NotNull(message = "{user.roles.notNull}")
    private Set<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
