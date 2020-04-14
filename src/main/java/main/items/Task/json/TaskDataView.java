package main.items.Task.json;
import lombok.*;
import main.items.Task.Enum.TaskStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskDataView {

    @NotNull
    private String title;

    @NotNull
    private String description;

    private float estimatedTime;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate deadlineDate;

    @NotNull
    private Long adminUserId;

    @NotNull
    private Long boardId;

    @NotNull
    private TaskStatus status;

    private Long assignee;

    private Long groupId;

}
