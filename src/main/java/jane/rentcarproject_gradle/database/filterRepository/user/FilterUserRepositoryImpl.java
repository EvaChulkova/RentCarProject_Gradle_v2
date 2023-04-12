package jane.rentcarproject_gradle.database.filterRepository.user;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.query.QPredicate;
import jane.rentcarproject_gradle.query.filter.UserFilter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static jane.rentcarproject_gradle.database.entity.QClient.client;
import static jane.rentcarproject_gradle.database.entity.QUser.user;
import static org.hibernate.graph.GraphSemantic.LOAD;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {
    private final EntityManager entityManager;

    @Override
    public List<User> findAllByUserFilter(UserFilter userFilter) {
        Predicate predicate = QPredicate.builder()
                .add(userFilter.getFirstName(), user.firstName::containsIgnoreCase)
                .add(userFilter.getLastname(), user.lastName::containsIgnoreCase)
                .add(userFilter.getLogin(), user.login::containsIgnoreCase)
                .buildAnd();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        Predicate predicate = QPredicate.builder()
                .add(login, user.login::eq)
                .add(password, user.password::eq)
                .buildAnd();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetchOne();
    }

    @Override
    public User findByLogin(String login) {
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(user.login.eq(login))
                .fetchOne();
    }

    @Override
    public User findClientInformationAboutUserByUserInfo(UserFilter userFilter) {
        Predicate predicate = QPredicate.builder()
                .add(userFilter.getFirstName(), user.firstName::eq)
                .add(userFilter.getLastname(), user.lastName::eq)
                .add(userFilter.getLogin(), user.login::eq)
                .buildAnd();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .join(user.client, client)
                .setHint(LOAD.getJpaHintName(), entityManager.getEntityGraph("withClient"))
                .where(predicate)
                .fetchOne();
    }
}
