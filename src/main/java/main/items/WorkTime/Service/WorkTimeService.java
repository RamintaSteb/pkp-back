package main.items.WorkTime.Service;

import main.items.WorkTime.json.WorkTimeView;

public interface WorkTimeService {
    void createNewSchedule(WorkTimeView workTimeView);
}
