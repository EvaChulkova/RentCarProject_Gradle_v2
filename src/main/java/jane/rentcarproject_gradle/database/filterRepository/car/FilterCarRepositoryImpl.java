package jane.rentcarproject_gradle.database.filterRepository.car;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jane.rentcarproject_gradle.database.entity.Car;
import jane.rentcarproject_gradle.database.entity.enums.CarStatusEnum;
import jane.rentcarproject_gradle.query.QPredicate;
import jane.rentcarproject_gradle.query.filter.CarFilter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static jane.rentcarproject_gradle.database.entity.QCar.car;

@RequiredArgsConstructor
public class FilterCarRepositoryImpl implements FilterCarRepository{
    private final EntityManager entityManager;

    @Override
    public List<Car> findAllByFilter(CarFilter carFilter) {
        Predicate predicate = QPredicate.builder()
                .add(carFilter.getBrand(), car.brand::eq)
                .add(carFilter.getModel(), car.model::eq)
                .add(carFilter.getColor(), car.color::eq)
                .add(carFilter.getSeatAmount(), car.seatAmount::eq)
                .add(carFilter.getPricePerDay(), car.pricePerDay::eq)
                .add(carFilter.getStatus(), car.status::eq)
                .buildAnd();

        return new JPAQuery<Car>(entityManager)
                .select(car)
                .from(car)
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Car> findCarsByStatus(CarStatusEnum carStatus) {
        Predicate predicate = QPredicate.builder()
                .add(carStatus, car.status::eq)
                .buildAnd();

        return new JPAQuery<Car>(entityManager)
                .select(car)
                .from(car)
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Car> findCarsInPriceRange(Integer minPrice, Integer maxPrice) {
        Predicate predicate = QPredicate.builder()
                .add(minPrice, car.pricePerDay::gt)
                .add(maxPrice, car.pricePerDay::lt)
                .buildAnd();

        return new JPAQuery<Car>(entityManager)
                .select(car)
                .from(car)
                .where(predicate)
                .fetch();
    }

    @Override
    public Car findCheapestCar() {
        return entityManager.createQuery("select c from Car c order by c.pricePerDay asc", Car.class)
                .setMaxResults(1)
                .getSingleResult();
    }
}
