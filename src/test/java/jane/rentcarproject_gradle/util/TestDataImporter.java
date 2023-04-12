package jane.rentcarproject_gradle.util;

import jane.rentcarproject_gradle.database.entity.Booking;
import jane.rentcarproject_gradle.database.entity.Car;
import jane.rentcarproject_gradle.database.entity.Client;
import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.database.entity.enums.BookingStatusEnum;
import jane.rentcarproject_gradle.database.entity.enums.CarColorEnum;
import jane.rentcarproject_gradle.database.entity.enums.CarStatusEnum;
import jane.rentcarproject_gradle.database.entity.enums.PaymentStateEnum;
import jane.rentcarproject_gradle.database.entity.enums.RoleEnum;
import lombok.experimental.UtilityClass;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Arrays;

@UtilityClass
public class TestDataImporter {
    public void importData(EntityManager entityManager) {

        User ivanAdmin = saveUser(entityManager, "Ivan", "Ivanov", "ivanAdmin@gmail.com", "123", RoleEnum.ADMIN);
        User annaAdmin = saveUser(entityManager, "Anna", "Sidorova", "annaAdmin@gmail.com", "123", RoleEnum.ADMIN);

        User svetlanaUser = saveUser(entityManager, "Svetlana", "Petrova", "sveta@gmail.com", "456", RoleEnum.CLIENT);
        User innaUser = saveUser(entityManager, "Inna", "Nikitina", "inna@gmail.com", "456", RoleEnum.CLIENT);
        User ninaUser = saveUser(entityManager, "Nina", "Monahova", "nina@gmail.com", "456", RoleEnum.CLIENT);
        User alekseyUser = saveUser(entityManager, "Aleksey", "Antoch", "aleks@gmail.com", "456", RoleEnum.CLIENT);
        User viktorUser = saveUser(entityManager, "Viktor", "Viktorov", "viktor@gmail.com", "456", RoleEnum.CLIENT);
        User petrUser = saveUser(entityManager, "Petr", "Sidorov", "petr@gmail.com", "456", RoleEnum.CLIENT);

        Client svetlana = saveClient(entityManager, svetlanaUser, LocalDate.of(2002, 2, 15), 136015, LocalDate.of(2038, 10, 10));
        Client inna = saveClient(entityManager, innaUser, LocalDate.of(1984, 7, 24), 116753, LocalDate.of(2027, 1, 10));
        Client nina = saveClient(entityManager, ninaUser, LocalDate.of(1998, 5, 18), 144096, LocalDate.of(2031, 7, 19));
        Client aleksey = saveClient(entityManager, alekseyUser, LocalDate.of(2001, 11, 6), 201410, LocalDate.of(2036, 4, 21));
        Client viktor = saveClient(entityManager, viktorUser, LocalDate.of(1978, 4, 17), 714002, LocalDate.of(2025, 9, 4));
        Client petr = saveClient(entityManager, petrUser, LocalDate.of(1989, 8, 22), 315698, LocalDate.of(2029, 9, 15));

        Car audiA3 = saveCar(entityManager, "Audi", "A3", CarColorEnum.BLACK, 4, 5450, CarStatusEnum.BOOKED, "AudiA3Image");
        Car audiRS6 = saveCar(entityManager, "Audi", "RS6", CarColorEnum.BLACK, 5, 9450, CarStatusEnum.AVAILABLE, "AudiRS6Image");
        Car kiaRio = saveCar(entityManager, "Kia", "Rio", CarColorEnum.WHITE, 4, 3170, CarStatusEnum.AVAILABLE, "KiaRioImage");
        Car BMWX5 = saveCar(entityManager, "BMW", "X5", CarColorEnum.BLACK, 5, 8915, CarStatusEnum.IN_CAR_SERVICE, "BMWX5Image");
        Car yellowRenaultLogan = saveCar(entityManager, "Renault", "Logan", CarColorEnum.BLACK, 4, 2100, CarStatusEnum.AVAILABLE, "YellowRenaultLoganImage");
        Car whiteRenaultLogan = saveCar(entityManager, "Renault", "Logan", CarColorEnum.YELLOW, 4, 2150, CarStatusEnum.AVAILABLE, "WhiteRenaultLoganImage");
        Car mercedesGLA200 = saveCar(entityManager, "Mercedes", "GLA 200", CarColorEnum.GREY, 5, 8900, CarStatusEnum.AVAILABLE, "MercedesGLA200Image");
        Car miniCooper = saveCar(entityManager, "Mini", "Cooper", CarColorEnum.RED, 2, 6920, CarStatusEnum.BOOKED, "MiniCooperImage");
        Car blackVWPolo = saveCar(entityManager, "VW", "Polo", CarColorEnum.BLACK, 4, 3450, CarStatusEnum.BOOKED, "BlackVWPoloImage");
        Car greyVWPolo = saveCar(entityManager, "VW", "Polo", CarColorEnum.GREY, 4, 3460, CarStatusEnum.AVAILABLE, "GreyVWPoloImage");
        Car VWTouareg = saveCar(entityManager, "VW", "Touareg", CarColorEnum.WHITE, 4, 7050, CarStatusEnum.BOOKED, "VWTouaregImage");
        Car toyotaCamry = saveCar(entityManager, "Toyota", "Camry", CarColorEnum.BLACK, 4, 4740, CarStatusEnum.AVAILABLE, "ToyotaCamryImage");

        addToBooking(entityManager,  LocalDate.of(2023, 2, 14), LocalDate.of(2023, 2, 17), BookingStatusEnum.COMPLETED, PaymentStateEnum.PAID, "Booking is completed. Nice to meet you again!", inna, kiaRio);
        addToBooking(entityManager, LocalDate.of(2023, 3, 16), LocalDate.of(2023, 3, 19), BookingStatusEnum.APPROVED, PaymentStateEnum.NOT_PAID, "Booking is approved. To pay...", inna, blackVWPolo);

        addToBooking(entityManager, LocalDate.of(2023, 2, 21), LocalDate.of(2023, 2, 27), BookingStatusEnum.COMPLETED, PaymentStateEnum.PAID, "Booking is completed. Nice to meet you again!", nina, miniCooper);
        addToBooking(entityManager, LocalDate.of(2023, 3, 11), LocalDate.of(2023, 3, 18), BookingStatusEnum.APPROVED, PaymentStateEnum.PAID, "Payment was successful! Have a good trip!", nina, miniCooper);

        addToBooking(entityManager, LocalDate.of(2023, 3, 11), LocalDate.of(2023, 3, 21), BookingStatusEnum.APPROVED, PaymentStateEnum.PAID, "Payment was successful! Have a good trip!", viktor, VWTouareg);

        addToBooking(entityManager, LocalDate.of(2023, 3, 6), LocalDate.of(2023, 3, 10), BookingStatusEnum.COMPLETED, PaymentStateEnum.PAID, "Booking is completed. Nice to meet you again!", aleksey, toyotaCamry);
        addToBooking(entityManager, LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 14), BookingStatusEnum.IN_PROGRESS, PaymentStateEnum.NOT_PAID, "Booking in progress. Please, wait for approving", aleksey, mercedesGLA200);

        addToBooking(entityManager, LocalDate.of(2023, 3, 13), LocalDate.of(2023, 3, 20), BookingStatusEnum.IN_PROGRESS, PaymentStateEnum.NOT_PAID, "Booking in progress. Please, wait for approving", svetlana, audiA3);

        addToBooking(entityManager, LocalDate.of(2023, 3, 20), LocalDate.of(2023, 3, 27), BookingStatusEnum.IN_PROGRESS, PaymentStateEnum.NOT_PAID, "Booking in progress. Please, wait for approving", petr, greyVWPolo);
    }

    private void addToBooking(EntityManager entityManager,
                              LocalDate rentalStart,
                              LocalDate rentalFinish,
                              BookingStatusEnum bookingStatus,
                              PaymentStateEnum paymentState,
                              String comment,
                              Client client,
                              Car... cars) {
        Arrays.stream(cars)
                .map(car -> Booking.builder()
                        .client(client)
                        .car(car)
                        .rentalStart(rentalStart)
                        .rentalFinish(rentalFinish)
                        .status(bookingStatus)
                        .paymentState(paymentState)
                        .comment(comment)
                        .build())
                .forEach(entityManager::persist);
    }


    private Car saveCar(EntityManager entityManager,
                        String brand,
                        String model,
                        CarColorEnum color,
                        Integer seatAmount,
                        Integer pricePerDay,
                        CarStatusEnum carStatus,
                        String image) {
        Car car = Car.builder()
                .brand(brand)
                .model(model)
                .color(color)
                .seatAmount(seatAmount)
                .pricePerDay(pricePerDay)
                .status(carStatus)
                .image(image)
                .build();
        entityManager.persist(car);

        return car;
    }

    private Client saveClient(EntityManager entityManager,
                              User user,
                              LocalDate birthDate,
                              Integer drivingLicenceNo,
                              LocalDate validity) {

        Client client = Client.builder()
                .user(user)
                .birthDate(birthDate)
                .drivingLicenceNo(drivingLicenceNo)
                .validity(validity)
                .build();
        entityManager.persist(client);

        return client;
    }

    private User saveUser(EntityManager entityManager,
                          String firstName,
                          String lastName,
                          String login,
                          String password,
                          RoleEnum roleEnum) {

        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .login(login)
                .password(password)
                .role(roleEnum)
                .build();
        entityManager.persist(user);

        return user;
    }
}
