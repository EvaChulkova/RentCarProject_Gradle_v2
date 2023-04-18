package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.Client;
import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.integration.IntegrationTestBase;
import jane.rentcarproject_gradle.query.filter.ClientFilter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class ClientRepositoryIT extends IntegrationTestBase {
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;


    @Test
    void findAllClientsByFilter() {
        ClientFilter clientFilter = ClientFilter.builder()
                .birthDate(LocalDate.of(2002, 2, 15))
                .drivingLicenceNo(136015)
                .validity(LocalDate.of(2038, 10, 10))
                .build();

        List<Client> allClientsByFilter = clientRepository.findAllByFilter(clientFilter);
        assertThat(allClientsByFilter).hasSize(1);
    }

    @Test
    void findClientByDrivingLicenceNoTest() {
        Integer drivingLicenceNo = 136015;
        
        Optional<Client> actualClientByDrivingLicenceNo = clientRepository.findByDrivingLicenceNo(drivingLicenceNo);

        assertTrue(actualClientByDrivingLicenceNo.isPresent());
        actualClientByDrivingLicenceNo.ifPresent(client -> assertEquals(drivingLicenceNo, actualClientByDrivingLicenceNo.get().getDrivingLicenceNo()));
    }

    @Test
    void findUserInfoAboutClientByDrivingLicenceNoTest() {
        Client actualResult = clientRepository.findUserInfoAboutClientByDrivingLicenceNo(136015);

        assertThat(actualResult.getDrivingLicenceNo()).isEqualTo(136015);
        assertThat(actualResult.getUser().getLogin()).isEqualTo("sveta@gmail.com");
    }

    @Test
    void saveClientTest() {
        User user = CreateMockObjects.createUserRepository();
        userRepository.save(user);

        Client client = CreateMockObjects.createClientRepository();
        client.setUser(user);
        clientRepository.save(client);

        Optional<Client> savedClient = clientRepository.findById(client.getId());

        assertThat(savedClient).isNotEmpty();
        assertThat(savedClient.get().getId()).isEqualTo(client.getId());
    }

    @Test
    void updateClientTest() {
        User user = CreateMockObjects.createUserRepository();
        userRepository.save(user);

        Client client = CreateMockObjects.createClientRepository();
        client.setUser(user);
        clientRepository.save(client);

        client.setDrivingLicenceNo(5);
        clientRepository.saveAndFlush(client);

        Optional<Client> actualResult = clientRepository.findById(client.getId());

        assertThat(actualResult.get().getDrivingLicenceNo()).isEqualTo(5);
        assertThat(actualResult).contains(client);
    }

    @Test
    void deleteClientTest() {
        User user = CreateMockObjects.createUserRepository();
        userRepository.save(user);

        Client client = CreateMockObjects.createClientRepository();
        client.setUser(user);
        clientRepository.save(client);

        clientRepository.delete(client);

        Optional<Client> actualResult = clientRepository.findById(client.getId());

        assertThat(actualResult).isEmpty();
    }

    @Test
    void findClientByIdTest() {
        User user = CreateMockObjects.createUserRepository();
        userRepository.save(user);

        Client client = CreateMockObjects.createClientRepository();
        client.setUser(user);
        clientRepository.save(client);

        Optional<Client> actualResult = clientRepository.findById(client.getId());

        assertThat(actualResult).isPresent();
        assertThat(actualResult).contains(client);
    }

    @Test
    void findAllClientsTest() {
        List<Client> actualResult = clientRepository.findAll();

        assertThat(actualResult).hasSize(6);
    }
}