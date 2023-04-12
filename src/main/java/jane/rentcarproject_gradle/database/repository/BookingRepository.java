package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.Booking;
import jane.rentcarproject_gradle.database.filterRepository.booking.FilterBookingRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingRepository extends
        JpaRepository<Booking, Long>,
        FilterBookingRepository { }
