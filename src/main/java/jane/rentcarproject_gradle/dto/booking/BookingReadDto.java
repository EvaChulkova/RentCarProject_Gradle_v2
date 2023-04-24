package jane.rentcarproject_gradle.dto.booking;

import jane.rentcarproject_gradle.database.entity.enums.BookingStatusEnum;
import jane.rentcarproject_gradle.database.entity.enums.PaymentStateEnum;
import jane.rentcarproject_gradle.dto.car.CarReadDto;
import jane.rentcarproject_gradle.dto.client.ClientReadDto;
import lombok.Value;

import java.time.LocalDate;

@Value
public class BookingReadDto {

    Long id;
    LocalDate rentalStart;
    LocalDate rentalFinish;
    BookingStatusEnum status;
    PaymentStateEnum paymentState;
    String comment;

    ClientReadDto clientReadDto;

    CarReadDto carReadDto;
}
