package main.items.WorkTime.Service;

import main.items.WorkTime.json.WorkTimeDataView;
import main.items.WorkTime.json.WorkTimeView;

import java.time.LocalDate;
import java.util.List;

public interface WorkTimeService {
    void createNewSchedule(WorkTimeView workTimeView);

    List<WorkTimeDataView> getScheduleData(Long id, String dateFrom, String dateTo);
}
