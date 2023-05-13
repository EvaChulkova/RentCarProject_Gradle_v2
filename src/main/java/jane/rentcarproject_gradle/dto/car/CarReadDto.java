package jane.rentcarproject_gradle.dto.car;

import jane.rentcarproject_gradle.database.entity.enums.CarColorEnum;
import jane.rentcarproject_gradle.database.entity.enums.CarStatusEnum;
import lombok.Value;

@Value
public class CarReadDto {
    Long id;
    String brand;
    String model;
    CarColorEnum color;
    Integer seatAmount;
    Integer pricePerDay;
    CarStatusEnum status;
}
