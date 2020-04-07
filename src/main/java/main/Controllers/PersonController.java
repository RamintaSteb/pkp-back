package main.Controllers;

import main.items.Person.Service.PersonService;
import main.items.Person.json.PersonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/signup")
    public @ResponseBody void addNewUser(@RequestBody PersonView personView) {
        personService.createNewUser(personView);
    }

}
