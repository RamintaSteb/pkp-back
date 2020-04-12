package main.items.WorkTime.Service.impl;

import lombok.RequiredArgsConstructor;
import main.items.WorkTime.Entity.WorkTime;
import main.items.WorkTime.Service.WorkTimeService;
import main.items.WorkTime.Repo.WorkTimeRepo;
import main.items.WorkTime.json.WorkTimeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class WorkTimeServiceImpl implements WorkTimeService {

    @Autowired
    private WorkTimeRepo workTimeRepo;

    @Override
    public void createNewSchedule(WorkTimeView workTimeView) {
        workTimeRepo.save(buildSchedule(workTimeView));
    }

    private WorkTime buildSchedule(WorkTimeView workTimeView) {
        return WorkTime.builder()
                .date(workTimeView.getWork_date())
                .from(workTimeView.getWorks_from())
                .to(workTimeView.getWorks_to())
                .workFromHome(workTimeView.is_working_from_home())
                .build();
    }

}
