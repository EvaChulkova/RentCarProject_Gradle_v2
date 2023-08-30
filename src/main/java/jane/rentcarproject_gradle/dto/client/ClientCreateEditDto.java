package jane.rentcarproject_gradle.dto.client;

import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Value
@FieldNameConstants
public class ClientCreateEditDto {
    Long id;

    Long userId;

    LocalDate birthDate;
    Integer drivingLicenceNo;
    LocalDate validity;
}
