package org.example.Entity;

import lombok.*;

import javax.persistence.*;;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"secondSurname","name","surname"})
@ToString(exclude = {"salary","projectTeams","positions"})
@Builder
@Entity
@Table(name = "employee", schema = "public")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(name = "second_surname")
    private String secondSurname;
    private LocalDate beginning;
    private LocalDate dismissal;

    @Builder.Default
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Salary> salary = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<ProjectTeam> projectTeams = new ArrayList<>();

    public void addSalary(Salary salary1) {
        salary.add(salary1);
        salary1.setEmployee(this);
    }

    public void addProjectTeam(ProjectTeam projectTeam) {
        projectTeams.add(projectTeam);
        projectTeam.setEmployee(this);
    }

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name= "employees_positions",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )
    private List<Position> positions = new ArrayList<>();

    public void addPosition(Position position){
        positions.add(position);
        position.getEmployees().add(this);
    }

}
