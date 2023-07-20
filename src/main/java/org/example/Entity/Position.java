package org.example.Entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import jakarta.persistence.*;
//import javax.persistence.*;;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString(exclude = "employees")
@Entity
@Table(name = "position", schema = "public")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;
    private LocalDate beginning;


    @Builder.Default
    @OneToMany (mappedBy = "position",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Employee> employees= new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setPosition(this);
    }



}
