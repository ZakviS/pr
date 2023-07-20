package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import jakarta.persistence.*;
//import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_type_id") // company_id
    @JsonBackReference
    private ProjectType projectType;

    @Builder.Default
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProjectTeam> projectTeams = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Revenue> revenue = new ArrayList<>();


    public void addProjectTeam(ProjectTeam projectTeam) {
        projectTeams.add(projectTeam);
        projectTeam.setProject(this);
    }
    public void addRevenue(Revenue revenue1) {
        revenue.add(revenue1);
        revenue1.setProject(this);
    }

}
