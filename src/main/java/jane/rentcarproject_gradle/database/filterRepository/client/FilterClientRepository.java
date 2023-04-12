package jane.rentcarproject_gradle.database.filterRepository.client;

import jane.rentcarproject_gradle.database.entity.Client;
import jane.rentcarproject_gradle.query.filter.ClientFilter;

import java.util.List;

public interface FilterClientRepository {
    List<Client> findAllByFilter(ClientFilter clientFilter);

    Client findClientByDrivingLicenceNo(Integer drivingLicenceNo);

    Client findUserInfoAboutClientByDrivingLicenceNo(Integer drivingLicenceNo);
}
