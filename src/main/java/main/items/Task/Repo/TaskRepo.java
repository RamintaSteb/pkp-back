package main.items.Task.Repo;

import main.items.Task.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo  extends JpaRepository<Task, Long> {

}
