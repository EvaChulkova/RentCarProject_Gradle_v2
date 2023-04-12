package jane.rentcarproject_gradle.query.filter;

import jane.rentcarproject_gradle.database.entity.enums.CarColorEnum;
import jane.rentcarproject_gradle.database.entity.enums.CarStatusEnum;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CarFilter {
    String brand;
    String model;
    CarColorEnum color;
    Integer seatAmount;
    Integer pricePerDay;
    CarStatusEnum status;
}
