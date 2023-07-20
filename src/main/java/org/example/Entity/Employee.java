package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@NamedEntityGraph(name = "employee.noFetch", attributeNodes = {})
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "position_id")
    private Position position;

    public void setPosition(Position position) {
        this.position = position;
        this.position.getEmployees().add(this);
    }



    @JsonManagedReference
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Salary> salary = new ArrayList<>();

//    @Builder.Default
    @JsonManagedReference
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

    @JsonManagedReference
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Premium> premiums = new ArrayList<>();



    public void addPremium(Premium premium) {
        premiums.add(premium);
        premium.setEmployee(this);
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Allowance> allowances = new ArrayList<>();

    public void addAllowance(Allowance allowance) {
        allowances.add(allowance);
        allowance.setEmployee(this);
    }


}
