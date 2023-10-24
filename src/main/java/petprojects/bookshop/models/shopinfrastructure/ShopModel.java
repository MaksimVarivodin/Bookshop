package petprojects.bookshop.models.shopinfrastructure;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table(name = "shops")
public class ShopModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "key_shop_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long shopId;

    @Column(nullable = false,
            length = 200)
    private String shopName;

    @Column(nullable = false,
            length = 200)
    private String address;

    @Column(nullable = false)
    private Integer workersAmount;

    @Column(nullable = false)
    private Integer bookStorageSize;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private CityModel city;

}
