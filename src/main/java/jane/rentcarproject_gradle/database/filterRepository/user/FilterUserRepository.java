package jane.rentcarproject_gradle.database.filterRepository.user;

import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.query.filter.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByUserFilter(UserFilter userFilter);

    User findClientInformationAboutUserByUserInfo(UserFilter userFilter);
}
