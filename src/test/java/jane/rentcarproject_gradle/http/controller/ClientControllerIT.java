package jane.rentcarproject_gradle.http.controller;

import jane.rentcarproject_gradle.database.entity.enums.RoleEnum;
import jane.rentcarproject_gradle.dto.client.ClientReadDto;
import jane.rentcarproject_gradle.dto.user.UserReadDto;
import jane.rentcarproject_gradle.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static jane.rentcarproject_gradle.dto.client.ClientCreateEditDto.Fields.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class ClientControllerIT extends IntegrationTestBase {
    private final MockMvc mockMvc;

    @Test
    @SneakyThrows
    void findAllClientsIT() {
        mockMvc.perform(get("/clients"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        view().name("client/clients"),
                        model().attributeExists("clients"),
                        model().attribute("clients", hasSize(6))
                );
    }

    @Test
    @SneakyThrows
    void findClientByIdIT() {
        UserReadDto userReadDto = new UserReadDto(
                3L,
                "Svetlana",
                "Petrova",
                "sveta@gmail.com",
                "456",
                RoleEnum.CLIENT
        );

        ClientReadDto clientReadDto = new ClientReadDto(
                1L,
                LocalDate.of(2022, 2, 15),
                136015,
                LocalDate.of(2027, 1, 10),
                userReadDto
        );

        mockMvc.perform(get("/clients/" + clientReadDto.getId()))
                .andExpectAll(
                        status().is2xxSuccessful(),
                        view().name("client/client"),
                        model().attributeExists("client"),
                        model().attribute("client", equalTo(clientReadDto))
                );
    }

    @Test
    @SneakyThrows
    void createClientIT() {
        UserReadDto userReadDto = new UserReadDto(
                100L,
                "TestUser",
                "TestUser",
                "testUser@gmail.com",
                "456",
                RoleEnum.CLIENT
        );

        mockMvc.perform(post("/clients")
                .param(userId, String.valueOf(100L))
                .param(birthDate, String.valueOf(LocalDate.of(1990, 1, 1)))
                .param(drivingLicenceNo, "9999999")
                .param(String.valueOf(LocalDate.of(2500, 1, 1)))
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/clients/{\\d+}")
                );
    }

    @Test
    @SneakyThrows
    void updateClientIT() {
        UserReadDto userReadDto = new UserReadDto(
                3L,
                "Svetlana",
                "Petrova",
                "sveta@gmail.com",
                "456",
                RoleEnum.CLIENT
        );

        ClientReadDto clientReadDto = new ClientReadDto(
                1L,
                LocalDate.of(2022, 2, 15),
                136015,
                LocalDate.of(2027, 1, 10),
                userReadDto
        );

        mockMvc.perform(post("/clients" + clientReadDto.getId() + "/update")
                        .param(userId, String.valueOf(1L))
                        .param(birthDate, String.valueOf(LocalDate.of(2022, 2, 15)))
                        .param(drivingLicenceNo, "136016")
                        .param(String.valueOf(LocalDate.of(2027, 1, 10)))
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/clients/" + clientReadDto.getId())
                );
    }

    @Test
    @SneakyThrows
    void deleteClientIT() {
        UserReadDto userReadDto = new UserReadDto(
                3L,
                "Svetlana",
                "Petrova",
                "sveta@gmail.com",
                "456",
                RoleEnum.CLIENT
        );

        ClientReadDto clientReadDto = new ClientReadDto(
                1L,
                LocalDate.of(2022, 2, 15),
                136015,
                LocalDate.of(2027, 1, 10),
                userReadDto
        );

        mockMvc.perform(post("/clients/" + clientReadDto.getId() + "/delete"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/clients")
                );
    }
}