package main.items.Person.Service.impl;

import lombok.RequiredArgsConstructor;
import main.items.Person.Entity.Person;
import main.items.Person.Repo.PersonRepo;
import main.items.Person.Service.PersonService;
import main.items.Person.json.LoginCredentialsView;
import main.items.Person.json.PersonEssentialDataView;
import main.items.Person.json.PersonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepo personRepo;

    @Override
    public void createNewUser(PersonView personView) {
        personRepo.save(buildPerson(personView));
    }

    @Override
    public PersonEssentialDataView login(LoginCredentialsView loginCredentialsView) {
        return buildPersonEssentialDataView(personRepo.findByUsernameAndPassword(loginCredentialsView.getUsername(),
                Integer.valueOf(loginCredentialsView.getPassword().hashCode())));
    }

    private PersonEssentialDataView buildPersonEssentialDataView(Person person) {
        return PersonEssentialDataView.builder()
                .id(person.getId())
                .name(person.getName())
                .surname(person.getSurname())
                .build();
    }

    private Person buildPerson(PersonView personView) {
       return Person.builder()
               .name(personView.getName())
               .surname(personView.getSurname())
               .username(personView.getUsername())
               .password(personView.getPassword().hashCode())
               .email(personView.getEmail())
               .address(personView.getAddress())
               .phoneNumber(personView.getPhoneNumber())
               .bios(personView.getShortBios())
               .build();
    }
}
