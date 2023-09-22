package petprojects.bookshop.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequestModel {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final Long phone;
    private final String profilePictureLink;
}
