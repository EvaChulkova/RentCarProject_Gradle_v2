package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.Car;
import jane.rentcarproject_gradle.database.entity.enums.CarColorEnum;
import jane.rentcarproject_gradle.database.entity.enums.CarStatusEnum;
import jane.rentcarproject_gradle.integration.IntegrationTestBase;
import jane.rentcarproject_gradle.query.filter.CarFilter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class CarRepositoryIT extends IntegrationTestBase {
    private static final CarStatusEnum AVAILABLE_CAR_STATUS = CarStatusEnum.AVAILABLE;
    private static final CarStatusEnum BOOKED_CAR_STATUS = CarStatusEnum.BOOKED;
    private static final CarStatusEnum IN_SERVICE_CAR_STATUS = CarStatusEnum.IN_CAR_SERVICE;

    private final CarRepository carRepository;


    @Test
    void findCarsAllByFilterTest() {
        CarFilter carFilter = CarFilter.builder()
                .brand("Audi")
                .model("RS6")
                .color(CarColorEnum.BLACK)
                .seatAmount(5)
                .pricePerDay(9450)
                .status(CarStatusEnum.AVAILABLE)
                .build();

        List<Car> allCarsByFilter = carRepository.findAllByFilter(carFilter);

        assertThat(allCarsByFilter).hasSize(1);
    }

    @Test
    void findCarsByStatusTest() {
        List<Car> availableCarsResult = carRepository.findCarsByStatus(AVAILABLE_CAR_STATUS);
        assertThat(availableCarsResult).hasSize(7);

        List<Car> bookedCarsResult = carRepository.findCarsByStatus(BOOKED_CAR_STATUS);
        assertThat(bookedCarsResult).hasSize(4);

        List<Car> inServiceCarsResult = carRepository.findCarsByStatus(IN_SERVICE_CAR_STATUS);
        assertThat(inServiceCarsResult).hasSize(1);
    }

    @Test
    void findCarsInPriceRangeTest() {
        int minCarPrice = 2000;
        int maxCarPrice = 6000;

        List<Car> carsResult = carRepository.findCarsInPriceRange(minCarPrice, maxCarPrice);
        carsResult.forEach(System.out::println);
        assertThat(carsResult).hasSize(7);

        List<Integer> carsPrice = carsResult.stream().map(Car::getPricePerDay).toList();
        assertThat(carsPrice).contains(2100, 2150, 3170, 3450, 3460, 4740, 5450);
    }

    @Test
    void findCheapestCarTest() {
        Car resultCar = carRepository.findCheapestCar();
        assertThat(resultCar.getPricePerDay()).isEqualTo(2100);
    }


    @Test
    void saveCarTest() {
        Car car = CreateMockObjects.createCarRepository();
        carRepository.save(car);

        Optional<Car> actualResult = carRepository.findById(car.getId());

        assertThat(actualResult).isNotEmpty();
        assertThat(actualResult.get().getId()).isEqualTo(car.getId());
    }

    @Test
    void updateCarTest() {
        Car car = CreateMockObjects.createCarRepository();
        carRepository.save(car);

        car.setColor(CarColorEnum.WHITE);
        carRepository.saveAndFlush(car);

        Optional<Car> actualResult = carRepository.findById(car.getId());

        assertThat(actualResult.get().getColor()).isEqualTo(CarColorEnum.WHITE);
        assertThat(actualResult).contains(car);
    }

    @Test
    void deleteCarTest() {
        Car car = CreateMockObjects.createCarRepository();;
        carRepository.save(car);

        carRepository.delete(car);

        Optional<Car> actualResult = carRepository.findById(car.getId());

        assertThat(actualResult).isEmpty();
    }

    @Test
    void findCarByIdTest() {
        Car car = CreateMockObjects.createCarRepository();
        carRepository.save(car);

        Optional<Car> actualResult = carRepository.findById(car.getId());

        assertThat(actualResult).isPresent();
        assertThat(actualResult).contains(car);
    }

    @Test
    void findAllCarsTest() {
        List<Car> actualResult = carRepository.findAll();

        assertThat(actualResult).hasSize(12);
    }
}