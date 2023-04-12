package jane.rentcarproject_gradle.database.filterRepository.booking;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jane.rentcarproject_gradle.database.entity.Booking;
import jane.rentcarproject_gradle.query.QPredicate;
import jane.rentcarproject_gradle.query.filter.BookingFilter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static jane.rentcarproject_gradle.database.entity.QBooking.booking;

@RequiredArgsConstructor
public class FilterBookingRepositoryImpl implements FilterBookingRepository {
    private final EntityManager entityManager;
    @Override
    public List<Booking> findAllByFilter(BookingFilter bookingFilter) {
        Predicate predicate = QPredicate.builder()
                .add(bookingFilter.getRentalStart(), booking.rentalStart::eq)
                .add(bookingFilter.getRentalFinish(),booking.rentalFinish::eq)
                .add(bookingFilter.getStatus(), booking.status::eq)
                .add(bookingFilter.getPaymentState(), booking.paymentState::eq)
                .buildAnd();

        return new JPAQuery<Booking>(entityManager)
                .select(booking)
                .from(booking)
                .where(predicate)
                .fetch();
    }
}
