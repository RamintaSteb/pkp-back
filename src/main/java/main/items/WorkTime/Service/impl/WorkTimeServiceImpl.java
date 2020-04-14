package main.items.WorkTime.Service.impl;

import lombok.RequiredArgsConstructor;
import main.items.Person.Repo.PersonRepo;
import main.items.WorkTime.Entity.WorkTime;
import main.items.WorkTime.Service.WorkTimeService;
import main.items.WorkTime.Repo.WorkTimeRepo;
import main.items.WorkTime.json.WorkTimeDataView;
import main.items.WorkTime.json.WorkTimeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class WorkTimeServiceImpl implements WorkTimeService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private WorkTimeRepo workTimeRepo;

    @Autowired
    private PersonRepo personRepo;

    @Override
    public void createNewSchedule(WorkTimeView workTimeView) {
        workTimeRepo.save(buildSchedule(workTimeView));
    }

    @Override
    public List<WorkTimeDataView> getScheduleData(Long id, String dateFrom, String dateTo) {
        return buildWorkTimeDataView(getWorkTimes(id, convertStringToDate(dateFrom), convertStringToDate(dateTo)));
    }

    private LocalDate convertStringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(date, formatter);
    }

    private List<WorkTime> getWorkTimes(Long id, LocalDate dateFrom, LocalDate dateTo) {
        //TODO: perduot normalia datas
//        LocalDate dateFrom = LocalDate.now();
//        LocalDate dateTo = LocalDate.now();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<WorkTime> cq = cb.createQuery(WorkTime.class);
        Root<WorkTime> root = cq.from(WorkTime.class);

        Predicate timeFromPredicate = cb.greaterThanOrEqualTo(root.<LocalDate>get("date"), dateFrom);
        Predicate timeToPredicate = cb.lessThanOrEqualTo(root.<LocalDate>get("date"), dateTo);
        Predicate idPredicate = cb.equal(root.get("person"), personRepo.findById(id).orElse(null));

        Predicate finalPredicate = cb.and(timeFromPredicate, timeToPredicate, idPredicate);
        cq.where(finalPredicate);

        return em.createQuery(cq).getResultList();

    }

    private List<WorkTimeDataView> buildWorkTimeDataView(List<WorkTime> workTimes) {
        List<WorkTimeDataView> workTimeDataViewList = new ArrayList<>();
        workTimes.forEach(workTime -> {
            workTimeDataViewList.add(WorkTimeDataView.builder()
                    .id(workTime.getId())
                    .date(workTime.getDate())
                    .from(workTime.getFrom())
                    .to(workTime.getTo())
                    .workFromHome(workTime.isWorkFromHome())
                    .build());
        });
        return workTimeDataViewList;
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
