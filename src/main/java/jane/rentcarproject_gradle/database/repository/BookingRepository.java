package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.Booking;
import jane.rentcarproject_gradle.database.entity.enums.BookingStatusEnum;
import jane.rentcarproject_gradle.database.entity.enums.PaymentStateEnum;
import jane.rentcarproject_gradle.database.filterRepository.booking.FilterBookingRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookingRepository extends
        JpaRepository<Booking, Long>,
        FilterBookingRepository {

    List<Booking> findAllByStatus(BookingStatusEnum bookingStatus);

    List<Booking> findAllByPaymentState(PaymentStateEnum paymentState);

}
