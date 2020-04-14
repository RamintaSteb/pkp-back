package main.items.Board.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import main.items.Person.Entity.Person;
import main.items.Task.Entity.Task;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardView {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Long adminUserId;

    private List<Long> assignedUsers;

    private List<Long> tasks;
}