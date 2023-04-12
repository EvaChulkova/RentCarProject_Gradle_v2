package jane.rentcarproject_gradle.database.repository;

import jane.rentcarproject_gradle.database.entity.Booking;
import jane.rentcarproject_gradle.database.entity.Car;
import jane.rentcarproject_gradle.database.entity.Client;
import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.database.entity.enums.BookingStatusEnum;
import jane.rentcarproject_gradle.database.entity.enums.CarColorEnum;
import jane.rentcarproject_gradle.database.entity.enums.CarStatusEnum;
import jane.rentcarproject_gradle.database.entity.enums.PaymentStateEnum;
import jane.rentcarproject_gradle.database.entity.enums.RoleEnum;

import java.time.LocalDate;

public class CreateMockObjects {
    public static Booking createBookingRepository() {
        Booking booking = Booking.builder()
                .client(createClientRepository())
                .car(createCarRepository())
                .rentalStart(LocalDate.of(2023, 3, 20))
                .rentalFinish(LocalDate.of(2023, 3, 25))
                .status(BookingStatusEnum.IN_PROGRESS)
                .paymentState(PaymentStateEnum.NOT_PAID)
                .comment("Waiting...")
                .build();
        return booking;
    }

    public static Car createCarRepository() {
        Car car = Car.builder()
                .brand("TestBrand")
                .model("TestModel")
                .color(CarColorEnum.BLACK)
                .seatAmount(4)
                .pricePerDay(5000)
                .status(CarStatusEnum.AVAILABLE)
                .build();
        return car;
    }

    public static Client createClientRepository() {
        Client client = Client.builder()
                .user(createUserRepository())
                .birthDate(LocalDate.of(2000, 1, 1))
                .drivingLicenceNo(1)
                .validity(LocalDate.of(2040, 1,1))
                .build();
        return client;
    }

    public static User createUserRepository() {
        User user = User.builder()
                .firstName("Test")
                .lastName("Testova")
                .login("test@gmail.com")
                .password("test")
                .role(RoleEnum.CLIENT)
                .build();
        return user;
    }
}
