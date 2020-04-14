package main.items.Person.Service;

import main.items.Person.json.LoginCredentialsView;
import main.items.Person.json.PersonEssentialDataView;
import main.items.Person.json.PersonView;

import java.util.List;

public interface PersonService {
    PersonEssentialDataView createNewUser(PersonView personView);

    PersonEssentialDataView login(LoginCredentialsView loginCredentialsView);
}
