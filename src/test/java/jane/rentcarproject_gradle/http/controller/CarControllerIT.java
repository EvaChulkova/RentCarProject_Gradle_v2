package jane.rentcarproject_gradle.http.controller;

import jane.rentcarproject_gradle.database.entity.enums.CarColorEnum;
import jane.rentcarproject_gradle.database.entity.enums.CarStatusEnum;
import jane.rentcarproject_gradle.dto.car.CarReadDto;
import jane.rentcarproject_gradle.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static jane.rentcarproject_gradle.dto.car.CarCreateEditDto.Fields.model;
import static jane.rentcarproject_gradle.dto.car.CarCreateEditDto.Fields.status;
import static jane.rentcarproject_gradle.dto.car.CarCreateEditDto.Fields.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class CarControllerIT extends IntegrationTestBase {
    private final MockMvc mockMvc;

    @Test
    @SneakyThrows
    void finAllCarsIT()  {
        mockMvc.perform(get("/cars"))
                .andExpectAll(
                        MockMvcResultMatchers.status().is2xxSuccessful(),
                        view().name("car/cars"),
                        model().attributeExists("cars"),
                        model().attribute("cars", hasSize(12))
                );
    }

    @Test
    @SneakyThrows
    void findCarByIdIT() {
        CarReadDto carReadDto = new CarReadDto(
                2L,
                "Audi",
                "RS6",
                CarColorEnum.BLACK,
                5,
                9450,
                CarStatusEnum.AVAILABLE
        );

        mockMvc.perform(get("/cars/" + carReadDto.getId()))
                .andExpectAll(
                       status().is2xxSuccessful(),
                       view().name("car/car"),
                       model().attributeExists("car"),
                       model().attribute("car", equalTo(carReadDto))
                );

    }


    @Test
    @SneakyThrows
    void createCarIT() {
        mockMvc.perform(post("/cars")
                .param(brand, "testBrand")
                .param(model, "testModel")
                .param(color, "BLACK")
                .param(seatAmount, "4")
                .param(pricePerDay, "9999")
                .param(status, "AVAILABLE")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/cars/{\\d+}")
                );
    }

    @Test
    @SneakyThrows
    void updateCarIT() {
        CarReadDto carReadDto = new CarReadDto(
                2L,
                "Audi",
                "RS6",
                CarColorEnum.BLACK,
                5,
                9450,
                CarStatusEnum.AVAILABLE
        );

        mockMvc.perform(post("/cars/" + carReadDto.getId() + "/update")
                .param(brand, "Audi")
                .param(model, "Q5")
                .param(color, "BLACK")
                .param(seatAmount, "5")
                .param(pricePerDay, "9450")
                .param(status, "AVAILABLE")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/cars/" + carReadDto.getId())
                );
    }

    @Test
    @SneakyThrows
    void deleteCarIT() {
        CarReadDto carReadDto = new CarReadDto(
                2L,
                "Audi",
                "RS6",
                CarColorEnum.BLACK,
                5,
                9450,
                CarStatusEnum.AVAILABLE
        );

        mockMvc.perform(post("/cars/" + carReadDto.getId() + "/delete"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrl("/cars")
                );
    }

}