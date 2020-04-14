package main.items.WorkTime.Repo;

import main.items.WorkTime.Entity.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTimeRepo extends JpaRepository<WorkTime, Long> {

}
