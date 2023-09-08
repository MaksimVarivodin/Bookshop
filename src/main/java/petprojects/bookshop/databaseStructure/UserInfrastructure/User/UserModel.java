package petprojects.bookshop.databaseStructure.UserInfrastructure.User;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import petprojects.bookshop.databaseStructure.UserInfrastructure.UserRoles;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table(name = "users")
public class UserModel implements UserDetails {

    @Id
    @SequenceGenerator(
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
    )
    @Column(nullable = false,
            unique = true,
            updatable = false)
    Long id;



    @Column(nullable = false, length = 100)
    String firstName;

    @Column(nullable = false, length = 100)
    String lastName;

    @Column(unique = true, nullable = false, length = 100)
    String email;

    @Column(nullable = false, length = 100)
    String password;

    @Column(nullable = false)
    Long phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    UserRoles role;

    @Column
    Boolean locked;

    @Column
    Boolean activated;

    @Column(nullable = true)
    String profilePictureLink;

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
    /**
     * Returns a collection of granted authorities for this object.
     *
     * @return  a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }
    /**
     * Retrieves the username of the user.
     *
     * @return  the concatenated first name and last name
     */
    @Override
    public String getUsername() {
        return firstName + " " + lastName;
    }

    /**
     * Checks if the account is non-expired.
     *
     * @return true if the account is non-expired, false otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * Determines if the account is locked or not.
     *
     * @return  true if the account is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }
    /**
     * Determines whether the user's credentials are non-expired.
     *
     * @return true if the credentials are non-expired, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * Determines if the function is enabled.
     *
     * @return  true if the function is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return activated;
    }
}
