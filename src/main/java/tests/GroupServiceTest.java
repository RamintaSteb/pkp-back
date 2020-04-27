package tests;

import main.items.Group.Entity.Group;
import main.items.Group.Repo.GroupRepo;
import main.items.Group.Service.impl.GroupServiceImpl;
import main.items.Group.json.GroupDataView;
import main.items.Group.json.GroupView;
import main.items.Person.Entity.Person;
import main.items.Person.Repo.PersonRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GroupServiceTest {

    @InjectMocks
    private GroupServiceImpl groupService;
    @Mock
    private GroupRepo groupRepo;
    @Mock
    private PersonRepo personRepo;

    private String title = "title";
    private String description = "descriprtion";
    private String name = "name";
    private String surname = "surname";
    private Long id = 1L;

    @Test
    void createNewGroup() {
        GroupView groupView = GroupView.builder()
                .title(title)
                .description(description)
                .administratorPersonId(id)
                .personListIds(new ArrayList<>())
                .build();

        Person personToReturnFromRepository = Person.builder()
                .id(id)
                .build();

        Group group = Group.builder()
                .id(id)
                .title(title)
                .description(description)
                .administratorPerson(personToReturnFromRepository)
                .personList(new ArrayList<>())
                .build();

        when(personRepo.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(personToReturnFromRepository));
        when(groupRepo.save(any(Group.class))).thenReturn(group);

        Long testId = groupService.createNewGroup(groupView);

        assertEquals(id, testId);
    }

    @Test
    void getGroupData() {
        Long groupId = id;

        Person personToReturnFromRepository = Person.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .tasks(new ArrayList<>())
                .build();

        Group group = Group.builder()
                .id(id)
                .title(title)
                .description(description)
                .administratorPerson(personToReturnFromRepository)
                .personList(new ArrayList<>())
                .build();

        when(groupRepo.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(group));

        GroupDataView groupDataView = groupService.getGroupData(groupId);

        assertEquals(id, groupDataView.getId());
        assertEquals(title, groupDataView.getTitle());
        assertEquals(description, groupDataView.getDescription());
        assertEquals(name + " " + surname, groupDataView.getAdministratorFullName());
        assertEquals(0, groupDataView.getNumberOfMembers());
        assertEquals(0L, groupDataView.getMembersTasksNumber());
        assertEquals(0, groupDataView.getMembersTasksTime());
    }

    @Test
    void findAllGroups() {
        Person personToReturnFromRepository = Person.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .tasks(new ArrayList<>())
                .build();

        List<Group> groups = new ArrayList<>();
        groups.add(Group.builder()
                .id(id)
                .title(title)
                .description(description)
                .administratorPerson(personToReturnFromRepository)
                .personList(new ArrayList<>())
                .build());

        when(groupRepo.findAll()).thenReturn(groups);

        GroupDataView groupDataView = groupService.findAllGroups().get(0);

        assertEquals(id, groupDataView.getId());
        assertEquals(title, groupDataView.getTitle());
        assertEquals(description, groupDataView.getDescription());
        assertEquals(name + " " + surname, groupDataView.getAdministratorFullName());
        assertEquals(0, groupDataView.getNumberOfMembers());
        assertEquals(0L, groupDataView.getMembersTasksNumber());
        assertEquals(0, groupDataView.getMembersTasksTime());
    }
}