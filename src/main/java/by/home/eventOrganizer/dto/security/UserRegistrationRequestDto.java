package by.home.eventOrganizer.dto.security;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserRegistrationRequestDto {

    @NotNull(message = "{user.login.notNull}")
    @NotEmpty(message = "{user.login.notEmpty}")
    @Size(min = 3, max = 50, message = "{user.name.size}")
    private String login;

    @NotNull(message = "{user.password.notNull}")
    @NotEmpty(message = "{user.password.notEmpty}")
    @Size(min = 3, max = 100, message = "{user.password.size}")
    private String password;

    @NotNull(message = "{user.roles.notNull}")
    private Set<String> roles;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
