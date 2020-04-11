package main.items.Person.json;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LoginCredentialsView {

    @NotNull(message = "username must be not null")
    private String username;

    @NotNull(message = "password must be not null")
    private String password;
}
