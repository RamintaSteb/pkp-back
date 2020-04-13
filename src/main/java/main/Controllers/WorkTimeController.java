package main.Controllers;

import main.items.WorkTime.Service.WorkTimeService;
import main.items.WorkTime.json.WorkTimeListView;
import main.items.WorkTime.json.WorkTimeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class WorkTimeController {

    @Autowired
    private WorkTimeService workTimeService;

    @PostMapping("/personalSchedule")
    public @ResponseBody void addNewSchedule(@RequestBody WorkTimeListView workTimeListView) {
        for (WorkTimeView workTimeView : workTimeListView.getWorkTimeLists()
             ) {workTimeService.createNewSchedule(workTimeView); }
    }
}