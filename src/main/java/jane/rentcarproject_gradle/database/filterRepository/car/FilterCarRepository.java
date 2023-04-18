package jane.rentcarproject_gradle.database.filterRepository.car;

import jane.rentcarproject_gradle.database.entity.Car;
import jane.rentcarproject_gradle.query.filter.CarFilter;

import java.util.List;

public interface FilterCarRepository {
    List<Car> findAllByFilter(CarFilter carFilter);
}
