package main.items.Person.Service;

import main.items.Person.json.LoginCredentialsView;
import main.items.Person.json.PersonEssentialDataView;
import main.items.Person.json.PersonView;

public interface PersonService {
    void createNewUser(PersonView personView);

    PersonEssentialDataView login(LoginCredentialsView loginCredentialsView);
}
