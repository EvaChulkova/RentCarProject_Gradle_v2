package jane.rentcarproject_gradle.mapper.car;

import jane.rentcarproject_gradle.database.entity.Car;
import jane.rentcarproject_gradle.dto.car.CarCreateEditDto;
import jane.rentcarproject_gradle.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CarCreateEditMapper implements Mapper<CarCreateEditDto, Car> {

    @Override
    public Car map(CarCreateEditDto fromObject, Car toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Car map(CarCreateEditDto object) {
        Car car = new Car();
        copy(object, car);

        return car;
    }

    private static void copy(CarCreateEditDto object, Car car) {
        car.setBrand(object.getBrand());
        car.setModel(object.getModel());
        car.setColor(object.getColor());
        car.setSeatAmount(object.getSeatAmount());
        car.setPricePerDay(object.getPricePerDay());
        car.setStatus(object.getStatus());
    }


}
