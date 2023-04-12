package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.Car;
import jane.rentcarproject_gradle.database.filterRepository.car.FilterCarRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends
        JpaRepository<Car, Long>,
        FilterCarRepository { }
