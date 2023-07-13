package org.example.Entity;
import lombok.*;
import jakarta.persistence.*;
//import javax.persistence.*;;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "payments")
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "payment_group", schema = "public")
public class PaymentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "paymentGroup")
    private List<Payment> payments = new ArrayList<>();
}
