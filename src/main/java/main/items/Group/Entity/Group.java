package main.items.Group.Entity;

import lombok.*;
import main.items.Person.Entity.Person;

import javax.persistence.*;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor
@Table(name = "persons_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrator_id")
    private Person administratorPerson;

}
