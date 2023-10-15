package petprojects.bookshop.models.orderinfrastructure;

import jakarta.persistence.*;
import lombok.*;
import petprojects.bookshop.models.userinfrastructure.UserModel;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE
    )
    @Column(    name = "key_order_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long orderId;
    @Column(nullable = false,
            unique = true)
    private Long cardNumber;
    @Column(nullable = false,
            unique = true)
    private Integer cvv;
    @Column(nullable = false)
    private Integer month;
    @Column(nullable = false)
    private Integer year;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserModel user;

    public OrderModel(Long cardNumber,
                      Integer cvv,
                      Integer month,
                      Integer year,
                      UserModel user) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.month = month;
        this.year = year;
        this.user = user;
    }
}
