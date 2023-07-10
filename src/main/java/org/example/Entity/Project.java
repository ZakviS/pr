package org.example.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(of = "name")
@ToString(exclude = {"projectType","projectTeams","revenue"})
@Table(name = "project", schema = "public")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate start_date;
    private LocalDate end_date;
    private Long sum;

    @ManyToOne
    @JoinColumn(name = "project_type_id") // company_id
    private ProjectType projectType;

    @Builder.Default
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<ProjectTeam> projectTeams = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Revenue> revenue = new HashSet<>();


    public void addProjectTeam(ProjectTeam projectTeam) {
        projectTeams.add(projectTeam);
        projectTeam.setProject(this);
    }
    public void addRevenue(Revenue revenue1) {
        revenue.add(revenue1);
        revenue1.setProject(this);
    }

}
