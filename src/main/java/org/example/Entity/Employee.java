package org.example.Entity;

import lombok.*;
import jakarta.persistence.*;
//import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"secondSurname","name","surname"})
@ToString(exclude = {"salary","projectTeams","premiums","allowances"})

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
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;

//    @Builder.Default
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Salary> salary = new ArrayList<>();

//    @Builder.Default
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ProjectTeam> projectTeams = new ArrayList<>();

    public void addSalary(Salary salary1) {
        salary.add(salary1);
        salary1.setEmployee(this);
    }

    public void addProjectTeam(ProjectTeam projectTeam) {
        projectTeams.add(projectTeam);
        projectTeam.setEmployee(this);
    }

//    @Builder.Default
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    public void setPosition(Position position) {
        this.position = position;
        this.position.getEmployees().add(this);
    }

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Premium> premiums = new ArrayList<>();



    public void addPremium(Premium premium) {
        premiums.add(premium);
        premium.setEmployee(this);
    }

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Allowance> allowances = new ArrayList<>();

    public void addAllowance(Allowance allowance) {
        allowances.add(allowance);
        allowance.setEmployee(this);
    }


}
