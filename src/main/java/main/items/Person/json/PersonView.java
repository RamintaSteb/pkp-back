package main.items.Person.json;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonView {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
    private String shortBios;
}
