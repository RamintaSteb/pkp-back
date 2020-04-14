package main.items.Task.Service.impl;

import lombok.RequiredArgsConstructor;
import main.items.Board.Repo.BoardRepo;
import main.items.Group.Repo.GroupRepo;
import main.items.Person.Repo.PersonRepo;
import main.items.Task.Entity.Task;
import main.items.Task.Repo.TaskRepo;
import main.items.Task.Service.TaskService;
import main.items.Task.json.TaskDataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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


    public void createNewTask(TaskDataView taskDataView) {
        taskRepo.save(buildTask(taskDataView));
    }

    private Task buildTask(TaskDataView taskDataView) {
        return Task.builder()
                .title(taskDataView.getTitle())
                .description(taskDataView.getDescription())
                .estimatedTime(taskDataView.getEstimatedTime())
                .status(taskDataView.getStatus())
                .startDate(taskDataView.getStartDate())
                .deadlineDate(taskDataView.getDeadlineDate())
                .adminUser(personRepo.findById(taskDataView.getAdminUserId()).orElse(null))
                .board(boardRepo.findById(taskDataView.getBoardId()).orElse(null))
                .group(groupRepo.findById(taskDataView.getGroupId()).orElse(null))
                .assignee(taskDataView.getAssignee() == null ? null : personRepo.findById(taskDataView.getAssignee()).orElse(null))
                .build();
    }
}
