package main.items.Person.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonEssentialDataView {

    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;
}
