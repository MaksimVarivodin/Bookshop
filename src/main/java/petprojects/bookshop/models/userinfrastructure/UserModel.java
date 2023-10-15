package petprojects.bookshop.models.userinfrastructure;

import jakarta.persistence.*;
import lombok.*;
import petprojects.bookshop.models.orderinfrastructure.OrderModel;

import java.util.Set;
//import org.springframework.security.coreGrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE
    )
    @Column(name = "key_user_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long userId;


    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private Long phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoles role;

    @Column
    private Boolean locked;

    @Column
    private Boolean activated;

    @Column(length = 2083)
    private String profilePictureLink;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<OrderModel> orders;

    public UserModel(String firstName,
                     String lastName,
                     String email,
                     String password,
                     Long phone,
                     UserRoles role,
                     Boolean locked,
                     Boolean activated,
                     String profilePictureLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.locked = locked;
        this.activated = activated;
        this.profilePictureLink = profilePictureLink;
    }



}
