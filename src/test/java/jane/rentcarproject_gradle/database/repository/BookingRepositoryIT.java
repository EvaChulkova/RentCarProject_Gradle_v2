package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.Booking;
import jane.rentcarproject_gradle.database.entity.Car;
import jane.rentcarproject_gradle.database.entity.Client;
import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.database.entity.enums.BookingStatusEnum;
import jane.rentcarproject_gradle.database.entity.enums.PaymentStateEnum;
import jane.rentcarproject_gradle.integration.IntegrationTestBase;
import jane.rentcarproject_gradle.query.filter.BookingFilter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RequiredArgsConstructor
class BookingRepositoryIT extends IntegrationTestBase {
    private static final BookingStatusEnum APPROVED_BOOKING_STATUS = BookingStatusEnum.APPROVED;
    private static final BookingStatusEnum IN_PROGRESS_BOOKING_STATUS = BookingStatusEnum.IN_PROGRESS;
    private static final BookingStatusEnum COMPLETED_BOOKING_STATUS = BookingStatusEnum.COMPLETED;
    private static final BookingStatusEnum REJECTED_BOOKING_STATUS = BookingStatusEnum.REJECTED;
    private static final BookingStatusEnum FOR_CANCELLING_BOOKING_STATUS = BookingStatusEnum.FOR_CANCELLING;
    private static final BookingStatusEnum CANCELLED_BOOKING_STATUS = BookingStatusEnum.CANCELLED;


    private static final PaymentStateEnum PAID_STATE = PaymentStateEnum.PAID;
    private static final PaymentStateEnum NOT_PAID_STATE = PaymentStateEnum.NOT_PAID;


    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;

    @Test
    void findAllBookingsByFilter() {
        BookingFilter bookingFilter = BookingFilter.builder()
                .rentalStart(LocalDate.of(2023, 3, 11))
                .rentalFinish(LocalDate.of(2023, 3, 18))
                .status(BookingStatusEnum.APPROVED)
                .paymentState(PaymentStateEnum.PAID)
                .build();

        List<Booking> allBookingsByFilter = bookingRepository.findAllByFilter(bookingFilter);

        assertThat(allBookingsByFilter).hasSize(1);
    }

    @Test
    void findAllBookingsByStatus() {
        List<Booking> approvedBookings = bookingRepository.findAllByStatus(APPROVED_BOOKING_STATUS);
        assertThat(approvedBookings).hasSize(3);

        List<Booking> inProgressBookings = bookingRepository.findAllByStatus(IN_PROGRESS_BOOKING_STATUS);
        assertThat(inProgressBookings).hasSize(3);

        List<Booking> completedBookings = bookingRepository.findAllByStatus(COMPLETED_BOOKING_STATUS);
        assertThat(completedBookings).hasSize(3);

        List<Booking> rejectedBookings = bookingRepository.findAllByStatus(REJECTED_BOOKING_STATUS);
        assertThat(rejectedBookings).hasSize(0);

        List<Booking> forCancellingBookings = bookingRepository.findAllByStatus(FOR_CANCELLING_BOOKING_STATUS);
        assertThat(forCancellingBookings).hasSize(0);

        List<Booking> cancelledBookings = bookingRepository.findAllByStatus(CANCELLED_BOOKING_STATUS);
        assertThat(cancelledBookings).hasSize(0);
    }

    @Test
    void findAllBookingByPaymentState() {
        List<Booking> paidBookings = bookingRepository.findAllByPaymentState(PAID_STATE);
        assertThat(paidBookings).hasSize(5);

        List<Booking> notPaidBookings = bookingRepository.findAllByPaymentState(NOT_PAID_STATE);
        assertThat(notPaidBookings).hasSize(4);
    }

    @Test
    void findAllBookingInPeriod() {
        LocalDate rentalStart = LocalDate.of(2023, 3, 1);
        LocalDate rentalFinish = LocalDate.of(2023, 3, 31);

        List<Booking> actualBookings = bookingRepository.findAllByRentalStartGreaterThanEqualAndRentalFinishLessThanEqual(rentalStart, rentalFinish);
        assertThat(actualBookings).hasSize(6);
    }

    @Test
    void saveBookingTest() {
        User user = CreateMockObjects.createUserRepository();
        userRepository.save(user);

        Client client = CreateMockObjects.createClientRepository();
        client.setUser(user);
        clientRepository.save(client);

        Car car = CreateMockObjects.createCarRepository();
        carRepository.save(car);

        Booking booking = CreateMockObjects.createBookingRepository();
        booking.setClient(client);
        booking.setCar(car);
        bookingRepository.save(booking);

        Optional<Booking> actualResult = bookingRepository.findById(booking.getId());

        assertThat(actualResult).isNotEmpty();
        assertThat(actualResult.get().getId()).isEqualTo(booking.getId());
    }

    @Test
    void updateBookingTest() {
        User user = CreateMockObjects.createUserRepository();
        userRepository.save(user);

        Client client = CreateMockObjects.createClientRepository();
        client.setUser(user);
        clientRepository.save(client);

        Car car = CreateMockObjects.createCarRepository();
        carRepository.save(car);

        Booking booking = CreateMockObjects.createBookingRepository();
        booking.setClient(client);
        booking.setCar(car);
        bookingRepository.save(booking);

        booking.setComment("Wait more, please...");
        bookingRepository.saveAndFlush(booking);

        Optional<Booking> actualResult = bookingRepository.findById(booking.getId());

        assertThat(actualResult.get().getComment()).isEqualTo("Wait more, please...");
    }

    @Test
    void deleteBookingTest() {
        User user = CreateMockObjects.createUserRepository();
        userRepository.save(user);

        Client client = CreateMockObjects.createClientRepository();
        client.setUser(user);
        clientRepository.save(client);

        Car car = CreateMockObjects.createCarRepository();
        carRepository.save(car);

        Booking booking = CreateMockObjects.createBookingRepository();
        booking.setClient(client);
        booking.setCar(car);
        bookingRepository.save(booking);

        bookingRepository.delete(booking);

        Optional<Booking> actualResult = bookingRepository.findById(booking.getId());
        assertThat(actualResult).isEmpty();
    }

    @Test
    void findBookingByIdTest() {
        User user = CreateMockObjects.createUserRepository();
        userRepository.save(user);

        Client client = CreateMockObjects.createClientRepository();
        client.setUser(user);
        clientRepository.save(client);

        Car car = CreateMockObjects.createCarRepository();
        carRepository.save(car);

        Booking booking = CreateMockObjects.createBookingRepository();
        booking.setClient(client);
        booking.setCar(car);
        bookingRepository.save(booking);

        Optional<Booking> actualResult = bookingRepository.findById(booking.getId());
        assertThat(actualResult).contains(booking);
    }

    @Test
    void findAllBookingsTest() {
        List<Booking> actualResult = bookingRepository.findAll();
        assertThat(actualResult).hasSize(9);
    }
}