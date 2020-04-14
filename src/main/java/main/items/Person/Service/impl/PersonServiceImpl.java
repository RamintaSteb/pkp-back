package main.items.Person.Service.impl;

import lombok.RequiredArgsConstructor;
import main.items.Person.Entity.Person;
import main.items.Person.Repo.PersonRepo;
import main.items.Person.Service.PersonService;
import main.items.Person.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepo personRepo;

    @Override
    public PersonEssentialDataView createNewUser(PersonView personView) {
        return buildPersonEssentialDataView(personRepo.save(buildPerson(personView)));
    }

    @Override
    public PersonEssentialDataView login(LoginCredentialsView loginCredentialsView) {
        return buildPersonEssentialDataView(personRepo.findByUsernameAndPassword(loginCredentialsView.getUsername(),
                Integer.valueOf(loginCredentialsView.getPassword().hashCode())));
    }

    @Override
    public void deleteUser(Long id) {
        personRepo.deleteById(id);
    }

    @Override
    public void updatePerson(FullPersonView fullPersonView) {
        personRepo.save(buildPerson(fullPersonView));
    }

    @Override
    public FullPersonView getUserData(Long id) {
        return buildPersonView(personRepo.findById(id).orElse(null));
    }

    @Override
    public void updatePassword(UpdatePasswordView updatePasswordView) {
        Person person = personRepo.findByIdAndPassword(updatePasswordView.getId(), updatePasswordView.getOldPassword().hashCode());
        personRepo.save(person.toBuilder()
                .password(updatePasswordView.getNewPassword().hashCode())
                .build());
    }

    private FullPersonView buildPersonView(Person person) {
        return FullPersonView.builder()
                .id(person.getId())
                .address(person.getAddress())
                .email(person.getEmail())
                .name(person.getName())
                .phoneNumber(person.getPhoneNumber())
                .shortBios(person.getBios())
                .surname(person.getSurname())
                .username(person.getUsername())
                .build();
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

    private Person buildPerson(FullPersonView personView) {
        Person person = personRepo.findById(personView.getId()).orElse(null);
        return Person.builder()
                .id(personView.getId())
                .name(personView.getName())
                .surname(personView.getSurname())
                .username(personView.getUsername())
                .email(personView.getEmail())
                .address(personView.getAddress())
                .phoneNumber(personView.getPhoneNumber())
                .bios(personView.getShortBios())
                .password(person.getPassword())
                .boards(person.getBoards())
                .tasks(person.getTasks())
                .group(person.getGroup())
                .workTimeList(person.getWorkTimeList())
                .build();
    }
}
