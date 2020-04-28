package main.items.Task.Service.impl;

import lombok.RequiredArgsConstructor;
import main.items.Board.Entity.Board;
import main.items.Board.Repo.BoardRepo;
import main.items.Group.Repo.GroupRepo;
import main.items.Person.Repo.PersonRepo;
import main.items.Task.Entity.Task;
import main.items.Task.Repo.TaskRepo;
import main.items.Task.Service.TaskService;
import main.items.Task.json.TaskDataMainView;
import main.items.Task.json.TaskDataView;
import main.items.Task.json.TaskUpdateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

@Service
@Validated
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private BoardRepo boardRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private GroupRepo groupRepo;

    @Override
    public TaskDataMainView getTaskData(Long id) {
        return buildTaskDataView(taskRepo.findById(id).orElse(null));
    }

    private TaskDataMainView buildTaskDataView(Task task) {
        return TaskDataMainView.builder()
                .assigneeFullName(task.getAssignee() != null ? task.getAssignee().getName() + " " + task.getAssignee().getSurname() : "")
                .assigneeId(task.getAssignee() != null ? task.getAssignee().getId() : null)
                .title(task.getTitle())
                .description(task.getDescription())
                .startDate(task.getStartDate())
                .deadlineDate(task.getDeadlineDate())
                .estimatedTime(task.getEstimatedTime())
                .build();
    }

    @Override
        public void createNewTask(TaskDataView taskDataView) {
        taskRepo.save(buildTask(taskDataView));
    }

    @Override
    public long updateTask(TaskUpdateView taskUpdateView) {
        Task task = taskRepo.findById(taskUpdateView.getId()).orElse(null);
        if (task == null) {
            throw new IllegalArgumentException();
        }

        return taskRepo.save(  task.toBuilder()
                .title(taskUpdateView.getTitle())
                .description(taskUpdateView.getDescription())
                .estimatedTime(taskUpdateView.getEstimatedTime())
                .startDate(taskUpdateView.getStartDate() == null ? LocalDate.now() : taskUpdateView.getStartDate())
                .deadlineDate(taskUpdateView.getDeadlineDate() == null ? LocalDate.now().plusMonths(1) : taskUpdateView.getDeadlineDate())
                .assignee(taskUpdateView.getAssignee() == null ? null : personRepo.findById(taskUpdateView.getAssignee()).orElse(null))
                .build()).getId();
    }

    private Task buildTask(TaskDataView taskDataView) {
        return Task.builder()
                .title(taskDataView.getTitle())
                .description(taskDataView.getDescription())
                .estimatedTime(taskDataView.getEstimatedTime())
                .status(taskDataView.getStatus())
                .startDate(taskDataView.getStartDate())
                .deadlineDate(taskDataView.getDeadlineDate())
                .adminUser(taskDataView.getAdminUserId() == null ? null : personRepo.findById(taskDataView.getAdminUserId()).orElse(null))
                .board(boardRepo.findById(taskDataView.getBoardId()).orElse(null))
                .group(groupRepo.findById(taskDataView.getGroupId()).orElse(null))
                .assignee(taskDataView.getAssignee() == null ? null : personRepo.findById(taskDataView.getAssignee()).orElse(null))
                .build();
    }
}
