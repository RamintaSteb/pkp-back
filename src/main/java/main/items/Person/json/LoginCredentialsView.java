package main.items.Person.json;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LoginCredentialsView {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
