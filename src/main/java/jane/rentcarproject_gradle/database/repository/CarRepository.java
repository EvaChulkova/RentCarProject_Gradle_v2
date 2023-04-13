package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.Car;
import jane.rentcarproject_gradle.database.entity.enums.CarStatusEnum;
import jane.rentcarproject_gradle.database.filterRepository.car.FilterCarRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends
        JpaRepository<Car, Long>,
        FilterCarRepository {

    List<Car> findAllByStatus(CarStatusEnum carStatusEnum);

    List<Car> findAllByPricePerDayBetween(Integer minPrice, Integer maxPrice);

    Optional<Car> findTopByOrderByPricePerDayAsc();
}
