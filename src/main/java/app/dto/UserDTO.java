package app.dto;

import app.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO
{
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer phone;

    public UserDTO(User user)
    {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }
}
