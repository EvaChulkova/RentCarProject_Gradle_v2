package jane.rentcarproject_gradle.validation.impl;

import jane.rentcarproject_gradle.dto.user.UserCreateEditDto;
import jane.rentcarproject_gradle.validation.UserInfo;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.springframework.util.StringUtils.hasText;

@Component
public class UserInfoValidator implements ConstraintValidator<UserInfo, UserCreateEditDto> {
    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext context) {
        return hasText(value.getFirstName()) || hasText(value.getLastName());
    }
}
