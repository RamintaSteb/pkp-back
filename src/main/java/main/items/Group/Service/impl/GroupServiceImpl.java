package main.items.Group.Service.impl;

import lombok.RequiredArgsConstructor;
import main.items.Group.Entity.Group;
import main.items.Group.Repo.GroupRepo;
import main.items.Group.Service.GroupService;
import main.items.Group.json.GroupDataView;
import main.items.Group.json.GroupView;
import main.items.Group.json.GroupViewForUpdate;
import main.items.Person.Entity.Person;
import main.items.Person.Repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Validated
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private PersonRepo personRepo;

    @Override
    public Long createNewGroup(GroupView groupView) {
        Group group = groupRepo.save(buildGroup(groupView));
        if (!groupView.getPersonListIds().isEmpty()) {
            groupView.getPersonListIds().forEach(personId -> updatePerson(group, personId));
        }
        return group.getId();
    }

    @Override
    public void updateGroup(GroupViewForUpdate groupViewForUpdate) {
        Group group = groupRepo.save(buildGroup(groupViewForUpdate));
        if (!groupViewForUpdate.getPersonListIds().isEmpty()) {
            groupViewForUpdate.getPersonListIds().forEach(personId -> updatePerson(group, personId));
        }
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepo.deleteById(id);
    }

    @Override
    public GroupDataView getGroupData(Long id) {
        return buildGroupDataView(groupRepo.findById(id).orElse(null));
    }

    @Override
    public List<GroupDataView> findAllGroups() {
        return buildGroupsInfo(groupRepo.findAll());
    }

    private List<GroupDataView> buildGroupsInfo(List<Group> groups) {
        List<GroupDataView> groupDataViews = new ArrayList<>();
        groups.forEach(group -> {
            groupDataViews.add(buildGroupDataView(group));
        });
        return groupDataViews;
    }

    private GroupDataView buildGroupDataView(Group group) {
        return GroupDataView.builder()
                .id(group.getId())
                .title(group.getTitle())
                .description(group.getDescription())
                .administratorFullName(group.getAdministratorPerson().getName() + " " + group.getAdministratorPerson().getSurname())
                .membersTasksNumber(getNumberOfTasks(group.getPersonList()))
                .membersTasksTime(getTasksTime(group.getPersonList()))
                .numberOfMembers(group.getPersonList().size())
                .build();
    }

    //ToDo: pacheckint ar veikia, greiciausiai ne :/
    private float getTasksTime(List<Person> personList) {
        AtomicReference<Float> time = new AtomicReference<>((float) 0);
        personList.forEach(person -> {
            person.getTasks().forEach(task -> {
                time.set(time.get() + task.getEstimatedTime());
            });
        });
        return time.get();
    }

    //ToDo: pacheckint ar veikia, greiciausiai ne :/
    private long getNumberOfTasks(List<Person> personList) {
        AtomicInteger tasks = new AtomicInteger();
        personList.forEach(person -> {
            tasks.set(tasks.get() + person.getTasks().size());
        });
        return Long.valueOf(tasks.get());
    }


    private Group buildGroup(GroupView groupView) {
        return Group.builder()
                .title(groupView.getTitle())
                .description(groupView.getDescription())
                .administratorPerson(personRepo.findById(groupView.getAdministratorPersonId()).orElse(null))
                .build();
    }

    private Group buildGroup(GroupViewForUpdate groupView) {
        return Group.builder()
                .id(groupView.getId())
                .title(groupView.getTitle())
                .description(groupView.getDescription())
                .administratorPerson(personRepo.findById(groupView.getAdministratorPersonId()).orElse(null))
                .build();
    }

    private void updatePerson(Group group, Long personId) {
        Person person = personRepo.findById(personId).orElse(null);
        personRepo.save(person.toBuilder()
                .group(group)
                .build());
    }
}
