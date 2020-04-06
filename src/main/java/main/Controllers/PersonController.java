package main.Controllers;


import main.items.Person.Entity.Person;
import main.items.Person.Repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {
    @Autowired
    private PersonRepo personRepo;

    @RequestMapping("/")
    public @ResponseBody
    String addNewUser() {
        return "";
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Person> getAllUsers() {
        // This returns a JSON or XML with the users
        return personRepo.findAll();
    }
}
