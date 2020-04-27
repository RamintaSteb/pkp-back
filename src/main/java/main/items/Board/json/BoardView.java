package main.items.Board.json;

import lombok.*;
import main.items.Task.json.TaskInformationView;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class BoardView {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Long adminUserId;

    private List<Long> assignedUsers;

    private List<TaskInformationView> taskData;
}
