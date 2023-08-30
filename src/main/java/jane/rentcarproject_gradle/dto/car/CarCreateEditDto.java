package jane.rentcarproject_gradle.dto.car;

import jane.rentcarproject_gradle.database.entity.enums.CarColorEnum;
import jane.rentcarproject_gradle.database.entity.enums.CarStatusEnum;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class CarCreateEditDto {
    String brand;
    String model;
    CarColorEnum color;
    Integer seatAmount;
    Integer pricePerDay;
    CarStatusEnum status;
}
