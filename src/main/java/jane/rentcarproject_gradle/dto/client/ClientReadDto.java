package jane.rentcarproject_gradle.dto.client;

import jane.rentcarproject_gradle.dto.user.UserReadDto;
import lombok.Value;

import java.time.LocalDate;

@Value
public class ClientReadDto {
    Long id;
    LocalDate birthDate;
    Integer drivingLicenceNo;
    LocalDate validity;

    UserReadDto userReadDto;
}
