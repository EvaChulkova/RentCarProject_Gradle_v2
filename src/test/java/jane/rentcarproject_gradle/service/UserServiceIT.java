package jane.rentcarproject_gradle.service;

import jane.rentcarproject_gradle.database.entity.enums.RoleEnum;
import jane.rentcarproject_gradle.dto.UserCreateEditDto;
import jane.rentcarproject_gradle.dto.UserReadDto;
import jane.rentcarproject_gradle.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {
    private static final Long SVETLANA_3_ID = 3L;
    private final UserService userService;

    @Test
    void findAllUsersIT() {
        List<UserReadDto> resultUsers = userService.findAll();
        assertThat(resultUsers).hasSize(8);
    }

    @Test
    void findUserByIdIT() {
        Optional<UserReadDto> maybeUser = userService.findById(SVETLANA_3_ID);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("sveta@gmail.com", user.getLogin()));
    }

    @Test
    void createUserIT() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto(
                "Test",
                "Test",
                "test@gmail.com",
                "123",
                RoleEnum.ADMIN
        );

        UserReadDto actualResult = userService.create(userCreateEditDto);

        assertEquals(userCreateEditDto.getFirstName(), actualResult.getFirstName());
        assertEquals(userCreateEditDto.getLastName(), actualResult.getLastName());
        assertEquals(userCreateEditDto.getLogin(), actualResult.getLogin());
        assertEquals(userCreateEditDto.getPassword(), actualResult.getPassword());
        assertSame(userCreateEditDto.getRole(), actualResult.getRole());
    }

    @Test
    void updateUserIT() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto(
                "Test",
                "Test",
                "test@gmail.com",
                "123",
                RoleEnum.ADMIN
        );

        Optional<UserReadDto> actualResult = userService.update(SVETLANA_3_ID, userCreateEditDto);
        assertTrue(actualResult.isPresent());

        actualResult.ifPresent(user -> {
            assertEquals(userCreateEditDto.getFirstName(), user.getFirstName());
            assertEquals(userCreateEditDto.getLastName(), user.getLastName());
            assertEquals(userCreateEditDto.getLogin(), user.getLogin());
            assertEquals(userCreateEditDto.getPassword(), user.getPassword());
            assertSame(userCreateEditDto.getRole(), user.getRole());
        });
    }

    @Test
    void deleteUserIT() {
        assertFalse(userService.delete(-124L));
        assertTrue(userService.delete(SVETLANA_3_ID));
    }
}
