package org.example.Entity;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

import javax.persistence.*;;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id","sum"})
@Builder
@Entity
@Table(name = "revenue", schema = "public")
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_payment")
    private LocalDate datePayment;
    private Long sum;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public void setProject(Project project) {
        this.project = project;
        this.project.getRevenue().add(this);
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
        this.payment.getRevenue().add(this);
    }
}
