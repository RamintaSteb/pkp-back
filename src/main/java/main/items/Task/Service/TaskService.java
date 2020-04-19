package main.items.Task.Service;

import main.items.Task.json.TaskDataView;
import main.items.Task.json.TaskUpdateView;

public interface TaskService {

    void createNewTask(TaskDataView taskDataView);

    long updateTask(TaskUpdateView taskUpdateView);
}
