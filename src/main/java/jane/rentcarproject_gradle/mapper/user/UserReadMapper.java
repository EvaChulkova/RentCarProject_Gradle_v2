package jane.rentcarproject_gradle.mapper.user;

import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.dto.user.UserReadDto;
import jane.rentcarproject_gradle.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {
    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getFirstName(),
                object.getLastName(),
                object.getLogin(),
                object.getPassword(),
                object.getRole()
        );
    }
}
