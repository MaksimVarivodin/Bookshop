package petprojects.bookshop.models.orderinfrastructure;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import petprojects.bookshop.models.userinfrastructure.UserModel;

import java.util.List;
import java.util.Set;

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
    @GeneratedValue
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


    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;


    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "order")
    private List<TakenModel> taken;

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
    @JsonBackReference
    public UserModel getUser() {
        return user;
    }
    @JsonManagedReference
    public List<TakenModel> getTaken() {
        return taken;
    }

}
