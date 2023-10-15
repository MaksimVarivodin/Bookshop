package petprojects.bookshop.models.shopinfrastructure;

import jakarta.persistence.*;
import lombok.*;
import petprojects.bookshop.models.literatureinfrastructure.LiteratureInfoModel;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table  (name = "counters")
public class PresentLiteratureModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE
    )
    @Column(    name = "key_counter_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long counterId;
    @Column(nullable = false)
    private Integer amount;
    @ManyToOne
    @JoinColumn(referencedColumnName = "key_literature_id", insertable = false)
    private LiteratureInfoModel literature;
    @ManyToOne
    @JoinColumn(referencedColumnName = "key_shop_id", insertable = false)
    private ShopModel shop;

    public PresentLiteratureModel(Integer amount,
                                  LiteratureInfoModel literature,
                                  ShopModel shop) {
        this.amount = amount;
        this.literature = literature;
        this.shop = shop;
    }

}
