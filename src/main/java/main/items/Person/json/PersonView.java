package main.items.Person.json;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonView {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String username;

    @NotNull
    private String email;

    private String address;

    private String phoneNumber;

    @NotNull
    private String password;

    @NotNull
    private String shortBios;
}
