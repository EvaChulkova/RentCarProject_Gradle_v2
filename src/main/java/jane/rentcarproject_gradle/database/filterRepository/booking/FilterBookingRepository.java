package jane.rentcarproject_gradle.database.filterRepository.booking;

import jane.rentcarproject_gradle.database.entity.Booking;
import jane.rentcarproject_gradle.query.filter.BookingFilter;

import java.util.List;

public interface FilterBookingRepository {
    List<Booking> findAllByFilter(BookingFilter bookingFilter);
}
