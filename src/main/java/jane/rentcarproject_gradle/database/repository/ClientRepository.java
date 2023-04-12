package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.Client;
import jane.rentcarproject_gradle.database.filterRepository.client.FilterClientRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends
        JpaRepository<Client, Long>,
        FilterClientRepository {

}
