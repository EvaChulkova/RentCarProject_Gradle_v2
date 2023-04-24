package jane.rentcarproject_gradle.http.controller;

import jane.rentcarproject_gradle.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class ClientControllerIT extends IntegrationTestBase {
    private final MockMvc mockMvc;

}