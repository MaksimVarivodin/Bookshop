package petprojects.bookshop.models.orderinfrastructure;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import petprojects.bookshop.models.shopinfrastructure.PresentLiteratureModel;

import java.time.LocalDate;

@Getter
@Setter
@ToString(exclude = "order")
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table(name = "taken")
public class TakenModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "key_taken_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long takenId;

    @Column(nullable = false)
    private Integer amount;

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;


    @ManyToOne
    @JoinColumn(name = "present_literature_id")
    private PresentLiteratureModel counter;

    public TakenModel( Integer amount, OrderModel order, PresentLiteratureModel counter) {
        this.amount = amount;
        this.order = order;
        this.counter = counter;
    }


    @JsonBackReference
    public OrderModel getOrder() {
        return order;
    }
}
