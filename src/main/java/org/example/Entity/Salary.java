package org.example.Entity;
import lombok.*;
import org.postgresql.util.PGmoney;
import jakarta.persistence.*;
//import javax.persistence.*;;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "salary", schema = "public")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sum;
    private LocalDate month;
    @Column(name = "number_of_order")
    private Long numberOfOrder;
    @Column(name = "date_of_order")
    private LocalDate dateOfOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id") // company_id
    private Employee employee;
}
