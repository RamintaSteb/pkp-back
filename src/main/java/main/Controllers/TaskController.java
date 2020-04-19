package main.Controllers;

import main.items.Task.Service.TaskService;
import main.items.Task.json.TaskDataView;
import main.items.Task.json.TaskUpdateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/createTask")
    public void addNewUser(@RequestBody TaskDataView taskDataView) {
        taskService.createNewTask(taskDataView);
    }

    @PostMapping("/updateTask")
    public long updateTask(@RequestBody TaskUpdateView taskUpdateView) {
        return taskService.updateTask(taskUpdateView);
    }

}
