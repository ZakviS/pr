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
@Builder
@Entity
@EqualsAndHashCode(of = "name")
@ToString(exclude = "project")
@Table(name = "project_type", schema = "public")
public class ProjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "projectType", cascade = CascadeType.ALL)
    private List<Project> project = new ArrayList<>();

    public void addProject(Project project1) {
        project.add(project1);
        project1.setProjectType(this);
    }
}
