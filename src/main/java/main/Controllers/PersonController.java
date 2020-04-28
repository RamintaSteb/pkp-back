package main.Controllers;

import main.items.Person.Service.PersonService;
import main.items.Person.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/signup")
    public @ResponseBody PersonEssentialDataView addNewUser(@RequestBody PersonView personView) {
        return personService.createNewUser(personView);
    }

    @PostMapping("/login")
    public PersonEssentialDataView login(@RequestBody LoginCredentialsView loginCredentialsView) {
        return personService.login(loginCredentialsView);
    }

    @PostMapping("/deleteUser")
    public void deleteUser(@RequestParam Long id) {
        personService.deleteUser(id);
    }

    @PostMapping("/updatePerson")
    public void updatePerson(@RequestBody FullPersonView fullPersonView) {
        personService.updatePerson(fullPersonView);
    }

    @GetMapping("/getUserData")
    public @ResponseBody FullPersonView getUserData(@RequestParam Long id) {
        return personService.getUserData(id);
    }

    @PostMapping("/updatePassword")
    public void updateUserPassword(@RequestBody UpdatePasswordView updatePasswordView) {
        personService.updatePassword(updatePasswordView);
    }

    @GetMapping("/getAllUsersForGroups")
    public @ResponseBody
    List<PersonEssentialDataView> getAllUsersForGroups() {
        return personService.getAllUsersForGroups();
    }

    @GetMapping("/getAllUsers")
    public @ResponseBody
    List<PersonEssentialDataView> getAllUsers() {
        return personService.getAllUsers();
    }

}
