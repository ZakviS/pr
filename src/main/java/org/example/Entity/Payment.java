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
@ToString(exclude = "revenue")
@EqualsAndHashCode(of = {"id","sum"})
@Builder
@Entity
@Table(name = "payment", schema = "public")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;
    private Long sum;
    private LocalDate month;
    private LocalDate year;


    @Builder.Default
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<Revenue> revenue = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "payment_group_id")
    private PaymentGroup paymentGroup;

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
        this.paymentType.getPayments().add(this);
    }

    public void setPaymentGroup(PaymentGroup paymentGroup) {
        this.paymentGroup = paymentGroup;
        this.paymentGroup.getPayments().add(this);
    }

    public void addRevenue(Revenue revenue1) {
        revenue.add(revenue1);
        revenue1.setPayment(this);
    }
}
