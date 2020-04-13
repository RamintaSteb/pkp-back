package main.items.WorkTime.Service.impl;

import lombok.RequiredArgsConstructor;
import main.items.Person.Repo.PersonRepo;
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

    @Autowired
    private PersonRepo personRepo;

    @Override
    public void createNewSchedule(WorkTimeView workTimeView) {
        workTimeRepo.save(buildSchedule(workTimeView));
    }

    private WorkTime buildSchedule(WorkTimeView workTimeView) {
        return WorkTime.builder()
                .person(personRepo.findById(workTimeView.getPersonId()).orElse(null))
                .date(workTimeView.getDate())
                .from(workTimeView.getFrom())
                .to(workTimeView.getTo())
                .workFromHome(workTimeView.isWorkFromHome())
                .build();
    }

}
