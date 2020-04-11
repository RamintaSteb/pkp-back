package main.items.Person.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonEssentialDataView {

    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;
}
