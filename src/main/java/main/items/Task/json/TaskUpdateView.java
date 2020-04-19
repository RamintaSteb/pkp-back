package main.items.Task.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskUpdateView {

    private long id;

    private String title;

    private String description;

    private float estimatedTime;

    private LocalDate startDate;

    private LocalDate deadlineDate;

    private Long assignee;
}
