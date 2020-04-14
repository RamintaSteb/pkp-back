package main.items.Person.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatePasswordView {

    @NotNull
    private Long id;

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;
}
