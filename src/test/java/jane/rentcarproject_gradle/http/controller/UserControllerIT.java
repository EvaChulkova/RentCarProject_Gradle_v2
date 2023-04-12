package jane.rentcarproject_gradle.http.controller;

import jane.rentcarproject_gradle.database.entity.enums.RoleEnum;
import jane.rentcarproject_gradle.dto.UserReadDto;
import jane.rentcarproject_gradle.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static jane.rentcarproject_gradle.dto.UserCreateEditDto.Fields.firstName;
import static jane.rentcarproject_gradle.dto.UserCreateEditDto.Fields.lastName;
import static jane.rentcarproject_gradle.dto.UserCreateEditDto.Fields.login;
import static jane.rentcarproject_gradle.dto.UserCreateEditDto.Fields.password;
import static jane.rentcarproject_gradle.dto.UserCreateEditDto.Fields.role;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerIT extends IntegrationTestBase {
    private static final Long SVETLANA_3_ID = 3L;
    private final MockMvc mockMvc;

    @Test
    void findAllUsersIT() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", hasSize(8)));
    }

    @Test
    void findUserByIdIT() throws Exception {
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
    void createUserIT() throws Exception {
        mockMvc.perform(post("/users")
                .param(firstName, "Test")
                .param(lastName, "Testova")
                .param(login, "test@gmail.com")
                .param(password, "123")
                .param(role, "ADMIN")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );

    }
}