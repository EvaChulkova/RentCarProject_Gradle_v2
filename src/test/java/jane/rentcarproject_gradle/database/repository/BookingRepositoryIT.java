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