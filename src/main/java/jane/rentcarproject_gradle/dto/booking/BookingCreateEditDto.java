package jane.rentcarproject_gradle.dto.booking;

import jane.rentcarproject_gradle.database.entity.enums.BookingStatusEnum;
import jane.rentcarproject_gradle.database.entity.enums.PaymentStateEnum;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Value
@FieldNameConstants
public class BookingCreateEditDto {
    Long id;

    Long clientId;
    Long carId;

    LocalDate rentalStart;
    LocalDate rentalFinish;
    BookingStatusEnum status;
    PaymentStateEnum paymentState;
    String comment;
}
