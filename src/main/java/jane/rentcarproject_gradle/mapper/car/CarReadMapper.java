package jane.rentcarproject_gradle.mapper.car;

import jane.rentcarproject_gradle.database.entity.Car;
import jane.rentcarproject_gradle.dto.car.CarReadDto;
import jane.rentcarproject_gradle.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CarReadMapper implements Mapper<Car, CarReadDto> {
    @Override
    public CarReadDto map(Car object) {
        return new CarReadDto(
                object.getId(),
                object.getBrand(),
                object.getModel(),
                object.getColor(),
                object.getSeatAmount(),
                object.getPricePerDay(),
                object.getStatus()
        );
    }
}
