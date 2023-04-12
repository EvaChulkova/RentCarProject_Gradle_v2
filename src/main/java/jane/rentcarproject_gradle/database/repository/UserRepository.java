package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.database.filterRepository.user.FilterUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        QuerydslPredicateExecutor<User> {

    @Query("select u from User u where u.login = :login")
    User findByLogin(String login);


    @Query("select u from User u " +
           "where u.firstName like %:firstname% and u.lastName like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

}
