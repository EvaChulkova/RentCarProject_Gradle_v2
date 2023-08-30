package jane.rentcarproject_gradle.http.controller;

import jane.rentcarproject_gradle.database.entity.enums.RoleEnum;
import jane.rentcarproject_gradle.dto.user.UserReadDto;
import jane.rentcarproject_gradle.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static jane.rentcarproject_gradle.dto.user.UserCreateEditDto.Fields.*;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerIT extends IntegrationTestBase {
    private final MockMvc mockMvc;

    @BeforeEach
    void init() {
        /*List<GrantedAuthority> roles = Arrays.asList(RoleEnum.ADMIN, RoleEnum.CLIENT);
        User testUser = new User("test@gmail.com", "test", roles);
        TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken(testUser, testUser.getPassword(), roles);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authenticationToken);
        SecurityContextHolder.setContext(securityContext);*/
    }

    @Test
    @SneakyThrows
    void findAllUsersIT() {
        mockMvc.perform(get("/users"))
                .andExpectAll(
                        status().is2xxSuccessful(),
                        view().name("user/users"),
                        model().attributeExists("users")
                );
    }

    @Test
    @SneakyThrows
    void findUserByIdIT() {
        UserReadDto userReadDto = new UserReadDto(
                3L,
                "Svetlana",
                "Petrova",
                "sveta@gmail.com",
                "456",
                RoleEnum.CLIENT
        );

        mockMvc.perform(get("/users/" + userReadDto.getId()))
                .andExpectAll(
                        status().is2xxSuccessful(),
                        view().name("user/user"),
                        model().attributeExists("user"),
                        model().attribute("user", equalTo(userReadDto))
                );
    }

    @Test
    @SneakyThrows
    void createUserIT() {
        mockMvc.perform(post("/users")
                .param(firstName, "Test")
                .param(lastName, "Testova")
                .param(login, "test@gmail.com")
                .param(rawPassword, "123")
                .param(role, "ADMIN")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );

    }

    @Test
    @SneakyThrows
    void updateUserIT() {
        UserReadDto userReadDto = new UserReadDto(
                3L,
                "Svetlana",
                "Petrova",
                "sveta@gmail.com",
                "456",
                RoleEnum.CLIENT
        );

        mockMvc.perform(post("/users/" + userReadDto.getId() + "/update")
                                .param(firstName, "Sveta")
                                .param(lastName, "Petrova")
                                .param(login, "sveta@gmail.com")
                                .param(rawPassword, "456")
                                .param(role, "CLIENT")
                        )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/users/" + userReadDto.getId())
                );
    }

    @Test
    @SneakyThrows
    void deleteUserIT() {
        UserReadDto userReadDto = new UserReadDto(
                3L,
                "Svetlana",
                "Petrova",
                "sveta@gmail.com",
                "456",
                RoleEnum.CLIENT
        );

        mockMvc.perform(post("/users/" + userReadDto.getId() + "/delete"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/users")
                );
    }
}