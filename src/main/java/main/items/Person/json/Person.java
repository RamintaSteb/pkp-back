package main.items.Person.json;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Person {
    private long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
    private String bios;
    
}
