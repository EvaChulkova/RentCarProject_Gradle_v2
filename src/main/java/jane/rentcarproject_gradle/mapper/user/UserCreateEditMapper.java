package jane.rentcarproject_gradle.mapper.user;

import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.dto.user.UserCreateEditDto;
import jane.rentcarproject_gradle.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);

        return user;
    }

    private static void copy(UserCreateEditDto object, User user) {
        user.setFirstName(object.getFirstName());
        user.setLastName(object.getLastName());
        user.setLogin(object.getLogin());
        user.setPassword(object.getPassword());
        user.setRole(object.getRole());
    }
}
