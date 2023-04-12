package jane.rentcarproject_gradle.dto;

import jane.rentcarproject_gradle.database.entity.enums.RoleEnum;
import jane.rentcarproject_gradle.validation.UserInfo;
import jane.rentcarproject_gradle.validation.group.CreateAction;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
@FieldNameConstants
@UserInfo(groups = CreateAction.class)
public class UserCreateEditDto {

    @Size(min = 3, max = 64)
    String firstName;

    String lastName;

    @Email
    String login;

    @NotNull
    String password;

    @NotNull
    RoleEnum role;
}
