package main.items.Person.Service;

import main.items.Person.json.*;

public interface PersonService {

    PersonEssentialDataView createNewUser(PersonView personView);

    PersonEssentialDataView login(LoginCredentialsView loginCredentialsView);

    void deleteUser(Long id);

    void updatePerson(FullPersonView fullPersonView);

    FullPersonView getUserData(Long id);

    void updatePassword(UpdatePasswordView updatePasswordView);
}
