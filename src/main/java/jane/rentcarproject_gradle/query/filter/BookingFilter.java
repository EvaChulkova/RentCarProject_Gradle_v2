package jane.rentcarproject_gradle.query.filter;

import jane.rentcarproject_gradle.database.entity.enums.BookingStatusEnum;
import jane.rentcarproject_gradle.database.entity.enums.PaymentStateEnum;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class BookingFilter {
    LocalDate rentalStart;
    LocalDate rentalFinish;
    BookingStatusEnum status;
    PaymentStateEnum paymentState;
}
