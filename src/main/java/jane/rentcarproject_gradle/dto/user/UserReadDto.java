package jane.rentcarproject_gradle.dto.user;

import jane.rentcarproject_gradle.database.entity.enums.RoleEnum;
import lombok.Value;

@Value
public class UserReadDto {
    Long id;
    String firstName;
    String lastName;
    String login;
    String password;
    RoleEnum role;
}
