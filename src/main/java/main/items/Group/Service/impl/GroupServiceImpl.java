package main.items.Group.Service.impl;

import lombok.RequiredArgsConstructor;
import main.items.Group.Entity.Group;
import main.items.Group.Repo.GroupRepo;
import main.items.Group.Service.GroupService;
import main.items.Group.json.GroupView;
import main.items.Group.json.GroupViewForUpdate;
import main.items.Person.Entity.Person;
import main.items.Person.Repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

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
        return groupRepo.save(buildGroup(groupView)).getId();
    }

    @Override
    public void updateGroup(GroupViewForUpdate groupViewForUpdate) {
        groupRepo.save(buildGroup(groupViewForUpdate));
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepo.deleteById(id);
    }

    private Group buildGroup(GroupView groupView) {
        return Group.builder()
                .title(groupView.getTitle())
                .description(groupView.getDescription())
                .administratorPerson(personRepo.findById(groupView.getAdministratorPersonId()).orElse(null))
                .personList(buildPersons(groupView.getPersonListIds()))
                .build();
    }

    private Group buildGroup(GroupViewForUpdate groupView) {
        return Group.builder()
                .id(groupView.getId())
                .title(groupView.getTitle())
                .description(groupView.getDescription())
                .administratorPerson(personRepo.findById(groupView.getAdministratorPersonId()).orElse(null))
                .personList(buildPersons(groupView.getPersonListIds()))
                .build();
    }

    private List<Person> buildPersons(List<Long> idsList) {
        List<Person> personList = new ArrayList<>();
        idsList.forEach(personId -> {
            personList.add(personRepo.findById(personId).orElse(null));
        });
        return personList;
    }
}
