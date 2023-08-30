package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.integration.IntegrationTestBase;
import jane.rentcarproject_gradle.query.filter.UserFilter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class UserRepositoryIT extends IntegrationTestBase {
    private final UserRepository userRepository;


    /*@Test
    void findUserByLoginTest() {
        String login = "nina@gmail.com";
        Optional<User> actualUserByLogin = userRepository.findByLogin(login);

        assertTrue(actualUserByLogin.isPresent());
        actualUserByLogin.ifPresent(user -> assertEquals(login, actualUserByLogin.get().getLogin()));
    }*/

    @Test
    void findUserByLoginAndPasswordTest() {
        String login = "inna@gmail.com";
        String password = "456";

        Optional<User> actualUserByLoginAndPassword = userRepository.findByLoginAndPassword(login, password);

        assertTrue(actualUserByLoginAndPassword.isPresent());
        actualUserByLoginAndPassword.ifPresent(user -> assertEquals(login, actualUserByLoginAndPassword.get().getLogin()));
    }

    @Test
    void findUsersByFirstnameAndLastnameTest() {
        List<User> users = userRepository.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase("a", "ov");
        assertThat(users).hasSize(4);
    }

    @Test
    void findAllUsersByUserFilter() {
        UserFilter filter = UserFilter.builder()
                .firstName("Svetlana")
                .lastname("Petrova")
                .login("sveta@gmail.com")
                .build();

        List<User> allUsersByUserFilter = userRepository.findAllByUserFilter(filter);

        assertThat(allUsersByUserFilter).hasSize(1);
    }


    @Test
    void findClientInformationAboutUserByUserInfoTest() {
        UserFilter userFilter = UserFilter.builder()
                .firstName("Inna")
                .lastname("Nikitina")
                .login("inna@gmail.com")
                .build();

        User expectedClientInfoByUserFilter = userRepository.findClientInformationAboutUserByUserInfo(userFilter);

        assertThat(expectedClientInfoByUserFilter.getFirstName()).isEqualTo("Inna");
        assertThat(expectedClientInfoByUserFilter.getLastName()).isEqualTo("Nikitina");
        assertThat(expectedClientInfoByUserFilter.getLogin()).isEqualTo("inna@gmail.com");
    }


    @Test
    void deleteUserTest() {
        User user = CreateMockObjects.createUserRepository();
        userRepository.save(user);

        userRepository.delete(user);

        Optional<User> actualResult = userRepository.findById(user.getId());
        assertTrue(actualResult.isEmpty());
        /*assertThat(actualResult).isEmpty();*/
    }

    @Test
    void saveUserTest() {
        User user = CreateMockObjects.createUserRepository();
        userRepository.save(user);

        Optional<User> savedUser = userRepository.findById(user.getId());

        assertTrue(savedUser.isPresent());
        assertThat(savedUser.get().getId()).isEqualTo(user.getId());
    }

    @Test
    void updateUserTest() {
        User user = CreateMockObjects.createUserRepository();
        userRepository.save(user);

        user.setLastName("NewTestova");
        userRepository.saveAndFlush(user);

        Optional<User> actualResult = userRepository.findById(user.getId());

        assertTrue(actualResult.isPresent());
        assertThat(actualResult.get().getLastName()).isEqualTo("NewTestova");
        assertThat(actualResult).contains(user);
    }

    @Test
    void findAllUsersTest() {
        List<User> actualResult = userRepository.findAll();

        assertThat(actualResult).hasSize(8);
    }
}