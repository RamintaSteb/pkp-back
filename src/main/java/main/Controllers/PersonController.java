package main.Controllers;

import main.items.Person.Service.PersonService;
import main.items.Person.json.LoginCredentialsView;
import main.items.Person.json.PersonEssentialDataView;
import main.items.Person.json.PersonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/signup")
    public @ResponseBody
    void addNewUser(@RequestBody PersonView personView) {
        personService.createNewUser(personView);
    }

    @PostMapping("/login")
    public PersonEssentialDataView login(@RequestBody LoginCredentialsView loginCredentialsView) {
        return personService.login(loginCredentialsView);
    }

}
