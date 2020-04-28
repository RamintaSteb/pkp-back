package main.items.Task.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskDataMainView {
    @NotNull
    private String title;

    @NotNull
    private String description;

    private float estimatedTime;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate deadlineDate;

    private String assigneeFullName;

    private Long assigneeId;
}
