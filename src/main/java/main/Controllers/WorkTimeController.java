package main.Controllers;

import main.items.WorkTime.Service.WorkTimeService;
import main.items.WorkTime.json.WorkTimeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class WorkTimeController {

    @Autowired
    private WorkTimeService workTimeService;

    @RequestMapping("/personalSchedule")
    public @ResponseBody void addNewSchedule(@RequestBody WorkTimeView workTimeView) {
        workTimeService.createNewSchedule(workTimeView);
    }
}