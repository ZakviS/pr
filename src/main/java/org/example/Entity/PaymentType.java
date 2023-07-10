package org.example.Entity;

import lombok.*;

import javax.persistence.*;
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
@Table(name = "payment_type", schema = "public")
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "paymentType")
    private List<Payment> payments = new ArrayList<>();
}
