package tests;

import main.items.Person.Entity.Person;
import main.items.Person.Repo.PersonRepo;
import main.items.Person.Service.impl.PersonServiceImpl;
import main.items.Person.json.FullPersonView;
import main.items.Person.json.LoginCredentialsView;
import main.items.Person.json.PersonEssentialDataView;
import main.items.Person.json.PersonView;
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
class PersonServiceTest {

    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private PersonRepo personRepo;

    private Long id = 1L;
    private String name = "name";
    private String surname = "surname";
    private String username = "username";
    private String email = "email";
    private String address = "address";
    private String phoneNumber = "phone number";
    private String password = "password";
    private String shortBios = "short bios";

    @Test
    void createNewUser() {
        // given
        PersonView personView = PersonView.builder()
                .name(name)
                .surname(surname)
                .username(username)
                .email(email)
                .address(address)
                .phoneNumber(phoneNumber)
                .password(password)
                .shortBios(shortBios)
                .build();

        Person userToReturnFromRepository = Person.builder()
                .name(name)
                .surname(surname)
                .build();
        when(personRepo.save(any(Person.class))).thenReturn(userToReturnFromRepository);

        // when
        PersonEssentialDataView testPerson = personService.createNewUser(personView);

        //then
        assertEquals(name, testPerson.getName());
        assertEquals(surname, testPerson.getSurname());
    }

    @Test
    void login() {
        LoginCredentialsView loginCredentialsView = LoginCredentialsView.builder()
                .username(username)
                .password(password)
                .build();

        Person userToReturnFromRepository = Person.builder()
                .name(name)
                .surname(surname)
                .build();
        when(personRepo.findByUsernameAndPassword(any(String.class), any(Integer.class)))
                .thenReturn(userToReturnFromRepository);

        PersonEssentialDataView testPerson = personService.login(loginCredentialsView);

        assertEquals(name, testPerson.getName());
        assertEquals(surname, testPerson.getSurname());
    }

    @Test
    void getUserData() {
        Person userToReturnFromRepository = Person.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .username(username)
                .email(email)
                .address(address)
                .phoneNumber(phoneNumber)
                .bios(shortBios)
                .build();
        when(personRepo.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(userToReturnFromRepository));

        FullPersonView testPerson = personService.getUserData(id);

        assertEquals(id, testPerson.getId());
        assertEquals(surname, testPerson.getSurname());
        assertEquals(name, testPerson.getName());
        assertEquals(username, testPerson.getUsername());
        assertEquals(email, testPerson.getEmail());
        assertEquals(address, testPerson.getAddress());
        assertEquals(phoneNumber, testPerson.getPhoneNumber());
        assertEquals(shortBios, testPerson.getShortBios());
    }

    @Test
    void getAllUsersForGroups() {
        List<Person> usersToReturnFromRepository = new ArrayList<>();
        usersToReturnFromRepository.add(Person.builder()
                .name(name)
                .surname(surname)
                .build());
        when(personRepo.findAll()).thenReturn(usersToReturnFromRepository);

        PersonEssentialDataView testPerson = personService.getAllUsersForGroups().get(0);

        assertEquals(name, testPerson.getName());
        assertEquals(surname, testPerson.getSurname());
    }

    @Test
    void getAllUsers() {
        List<Person> usersToReturnFromRepository = new ArrayList<>();
        usersToReturnFromRepository.add(Person.builder()
                .name(name)
                .surname(surname)
                .build());
        when(personRepo.findAll()).thenReturn(usersToReturnFromRepository);

        PersonEssentialDataView testPerson = personService.getAllUsersForGroups().get(0);

        assertEquals(name, testPerson.getName());
        assertEquals(surname, testPerson.getSurname());
    }
}