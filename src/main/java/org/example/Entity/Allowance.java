package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

//import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "allowance", schema = "public")
public class Allowance {
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
    @JsonBackReference
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
