package main.items.Person.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FullPersonView {

    @NotNull
    private Long id;

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
    private String shortBios;
}
