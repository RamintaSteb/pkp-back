package main.Controllers;

import main.items.WorkTime.Service.WorkTimeService;
import main.items.WorkTime.json.WorkTimeDataView;
import main.items.WorkTime.json.WorkTimeListView;
import main.items.WorkTime.json.WorkTimeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
public class WorkTimeController {

    @Autowired
    private WorkTimeService workTimeService;

    @PostMapping("/personalSchedule")
    public @ResponseBody
    void addNewSchedule(@RequestBody WorkTimeListView workTimeListView) {
        for (WorkTimeView workTimeView : workTimeListView.getWorkTimeLists()
        ) {
            workTimeService.createNewSchedule(workTimeView);
        }
    }

    @GetMapping("/Schedule")
    public @ResponseBody
    List<WorkTimeDataView> addScheduleData(@RequestParam Long id, @RequestParam String dateFrom, @RequestParam String dateTo) {
        return workTimeService.getScheduleData(id, dateFrom, dateTo);
    }

}