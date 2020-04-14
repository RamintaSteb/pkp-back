package main.items.Board.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardEssentialsView {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    private int numberOfMembers;
}
