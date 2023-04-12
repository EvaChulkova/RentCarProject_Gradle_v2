package jane.rentcarproject_gradle.database.filterRepository.client;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jane.rentcarproject_gradle.database.entity.Client;
import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.query.QPredicate;
import jane.rentcarproject_gradle.query.filter.ClientFilter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static jane.rentcarproject_gradle.database.entity.QClient.client;
import static org.hibernate.graph.GraphSemantic.LOAD;

@RequiredArgsConstructor
public class FilterClientRepositoryImpl implements FilterClientRepository {
    private final EntityManager entityManager;

    @Override
    public List<Client> findAllByFilter(ClientFilter clientFilter) {
        Predicate predicate = QPredicate.builder()
                .add(clientFilter.getBirthDate(), client.birthDate::eq)
                .add(clientFilter.getValidity(), client.validity::eq)
                .add(clientFilter.getDrivingLicenceNo(), client.drivingLicenceNo::eq)
                .buildAnd();

        return new JPAQuery<User>(entityManager)
                .select(client)
                .from(client)
                .where(predicate)
                .fetch();
    }

    @Override
    public Client findClientByDrivingLicenceNo(Integer drivingLicenceNo) {
        return new JPAQuery<Client>(entityManager)
                .select(client)
                .from(client)
                .where(client.drivingLicenceNo.eq(drivingLicenceNo))
                .fetchOne();
    }

    @Override
    public Client findUserInfoAboutClientByDrivingLicenceNo(Integer drivingLicenceNo) {
        Predicate predicate = QPredicate.builder()
                .add(drivingLicenceNo, client.drivingLicenceNo::eq)
                .buildAnd();

        return  new JPAQuery<Client>(entityManager)
                .select(client)
                .from(client)
                .setHint(LOAD.getJpaHintName(), entityManager.getEntityGraph("withUser"))
                .where(predicate)
                .fetchOne();
    }
}
