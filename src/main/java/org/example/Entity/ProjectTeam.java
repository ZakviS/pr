package org.example.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import jakarta.persistence.*;
//import javax.persistence.*;;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"startDate","endDate"})
@Builder
@Entity
@Table(name = "project_team", schema = "public")
public class ProjectTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    private double staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;

    public void setEmployee(Employee employee) {
        this.employee = employee;
        this.employee.getProjectTeams().add(this);
    }

    public void setProject(Project project) {
        this.project = project;
        this.project.getProjectTeams().add(this);
    }

}
