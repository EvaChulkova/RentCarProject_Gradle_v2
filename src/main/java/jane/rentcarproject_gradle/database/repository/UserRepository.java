package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.database.filterRepository.user.FilterUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        QuerydslPredicateExecutor<User> {

    Optional<User> findByLogin(String login);

    Optional<User> findByLoginAndPassword(String login, String password);

    List<User> findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstname, String lastname);

}
