package main.items.Task.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import main.items.Task.Enum.TaskStatus;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskInformationView {

    private long id;

    private String title;

    private float estimatedTime;

    private long adminUserId;

    private TaskStatus status;
}
