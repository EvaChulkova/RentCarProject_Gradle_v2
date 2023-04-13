package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.Client;
import jane.rentcarproject_gradle.database.filterRepository.client.FilterClientRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends
        JpaRepository<Client, Long>,
        FilterClientRepository {

    Optional<Client> findByDrivingLicenceNo(Integer drivingLicenceNo);
}
