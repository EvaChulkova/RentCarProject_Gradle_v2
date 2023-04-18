package jane.rentcarproject_gradle.mapper;

import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.database.entity.enums.RoleEnum;
import jane.rentcarproject_gradle.dto.user.UserReadDto;
import jane.rentcarproject_gradle.mapper.user.UserReadMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserReadMapperTest {
    @InjectMocks
    private UserReadMapper userReadMapper;

    @Test
    void mapReadUserTest() {
        User user = User.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Testova")
                .login("test@gmail.com")
                .password("123")
                .role(RoleEnum.ADMIN)
                .build();

        UserReadDto actualResult = userReadMapper.map(user);

        assertThat(actualResult.getId()).isEqualTo(user.getId());
    }
}