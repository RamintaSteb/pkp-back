package main.items.Person.Repo;

import main.items.Person.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {

    Person findByUsernameAndPassword(String username, Integer password);
}
