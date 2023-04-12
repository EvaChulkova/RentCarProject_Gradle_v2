package jane.rentcarproject_gradle.mapper;

import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.database.entity.enums.RoleEnum;
import jane.rentcarproject_gradle.dto.UserCreateEditDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserCreateEditMapperTest {
    @InjectMocks
    private  UserCreateEditMapper userCreateEditMapper;


    @Test
    void mapCreateEditUserTest() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "Test",
                "Testova",
                "test@gmail.com",
                "123",
                RoleEnum.CLIENT
        );
        User actualResult = userCreateEditMapper.map(userDto);

        assertThat(actualResult.getLogin()).isEqualTo(userDto.getLogin());
    }
}