package main.items.WorkTime.Entity;

import lombok.*;
import main.items.Person.Entity.Person;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor
@Table(name = "work_time")
public class WorkTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "work_date", nullable = false)
    private LocalDate date;

    @Column(name = "works_from", nullable = false)
    private Time from;

    @Column(name = "works_to", nullable = false)
    private Time to;

    @Column(name = "is_working_from_home", nullable = false)
    private boolean workFromHome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

}

