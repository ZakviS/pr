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
//@EqualsAndHashCode
@Builder
@ToString(exclude = "employees")
@Entity
@Table(name = "position", schema = "public")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;

    @Builder.Default
    @ManyToMany(mappedBy = "positions")
    private List<Employee> employees = new ArrayList<>();


}
