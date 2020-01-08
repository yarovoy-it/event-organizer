package by.home.eventOrganizer.dto.security;

import by.home.eventOrganizer.dto.BaseDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoleDto extends BaseDto {

    @NotNull(message = "{role.name.notNull}")
    @NotEmpty(message = "{role.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{role.name.size}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
