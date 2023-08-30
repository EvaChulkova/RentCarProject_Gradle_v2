package jane.rentcarproject_gradle.integration;

import jane.rentcarproject_gradle.annotation.IT;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

@IT
@Sql({
        "classpath:sql/data.sql"
})
@WithMockUser(username = "test@gmail.com", password = "test", authorities = {"ADMIN", "CLIENT"})
public abstract class IntegrationTestBase {
    private static final PostgreSQLContainer<?> springRentcarGradlePostgresContainer = new PostgreSQLContainer<>("postgres:15.2");

    @BeforeAll
    static void startContainer() {
        springRentcarGradlePostgresContainer.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", springRentcarGradlePostgresContainer::getJdbcUrl);
    }
}
