package main.items.Person.Service;

import lombok.NoArgsConstructor;
import main.items.Person.json.*;

import java.util.List;

public interface PersonService {

    PersonEssentialDataView createNewUser(PersonView personView);

    PersonEssentialDataView login(LoginCredentialsView loginCredentialsView);

    void deleteUser(Long id);

    void updatePerson(FullPersonView fullPersonView);

    FullPersonView getUserData(Long id);

    void updatePassword(UpdatePasswordView updatePasswordView);

    List<PersonEssentialDataView> getAllUsersForGroups();

    List<PersonEssentialDataView> getAllUsers();
}
