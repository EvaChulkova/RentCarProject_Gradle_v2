package jane.rentcarproject_gradle.query.filter;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ClientFilter {
    LocalDate birthDate;
    Integer drivingLicenceNo;
    LocalDate validity;
}
