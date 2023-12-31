package petprojects.bookshop.models.userinfrastructure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.repository.cdi.Eager;
import petprojects.bookshop.models.orderinfrastructure.OrderModel;

import java.util.ArrayList;
import java.util.List;
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
@ToString

public class UserModel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
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

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<OrderModel> orders;
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
