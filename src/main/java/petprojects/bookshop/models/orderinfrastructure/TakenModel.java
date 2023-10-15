package petprojects.bookshop.models.orderinfrastructure;

import jakarta.persistence.*;
import lombok.*;
import petprojects.bookshop.models.shopinfrastructure.PresentLiteratureModel;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table(name = "taken")
public class TakenModel {
    @Id

    @GeneratedValue(
            strategy = GenerationType.TABLE
    )
    @Column(name = "key_taken_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long takenId;
    @Column(nullable = false,
            unique = true)
    private LocalDate date;
    @Column(nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false)
    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "present_literature_id", insertable = false)
    private PresentLiteratureModel counter;

    public TakenModel(LocalDate date, Integer amount, OrderModel order, PresentLiteratureModel counter) {
        this.date = date;
        this.amount = amount;
        this.order = order;
        this.counter = counter;
    }
}
