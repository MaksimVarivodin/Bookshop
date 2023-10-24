package petprojects.bookshop.models.orderinfrastructure;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import petprojects.bookshop.models.userinfrastructure.UserModel;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@ToString(exclude = "user")
@Table(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(    name = "key_order_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long orderId;
    @Column(nullable = false,
            unique = true)
    private LocalDateTime date;
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

    @Getter(AccessLevel.NONE)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;



    @OneToMany(mappedBy = "order")
    private List<TakenModel> taken;

    public OrderModel(Long cardNumber,
                      LocalDateTime date,
                      Integer cvv,
                      Integer month,
                      Integer year,
                      UserModel user) {
        this.cardNumber = cardNumber;
        this.date = date;
        this.cvv = cvv;
        this.month = month;
        this.year = year;
        this.user = user;
    }
    @JsonBackReference
    public UserModel getUser() {
        return user;
    }


}
