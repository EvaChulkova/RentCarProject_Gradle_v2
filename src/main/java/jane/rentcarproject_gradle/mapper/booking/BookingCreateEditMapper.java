package jane.rentcarproject_gradle.mapper.booking;

import jane.rentcarproject_gradle.database.entity.Booking;
import jane.rentcarproject_gradle.database.entity.Car;
import jane.rentcarproject_gradle.database.entity.Client;
import jane.rentcarproject_gradle.database.repository.CarRepository;
import jane.rentcarproject_gradle.database.repository.ClientRepository;
import jane.rentcarproject_gradle.dto.booking.BookingCreateEditDto;
import jane.rentcarproject_gradle.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingCreateEditMapper implements Mapper<BookingCreateEditDto, Booking> {
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;

    @Override
    public Booking map(BookingCreateEditDto fromObject, Booking toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Booking map(BookingCreateEditDto object) {
        Booking booking = new Booking();
        copy(object, booking);
        return booking;
    }

    private void copy(BookingCreateEditDto object, Booking booking) {
        booking.setRentalStart(object.getRentalStart());
        booking.setRentalFinish(object.getRentalFinish());
        booking.setStatus(object.getStatus());
        booking.setPaymentState(object.getPaymentState());
        booking.setComment(object.getComment());
        booking.setClient(getClient(object.getClientId()));
        booking.setCar(getCar(object.getCarId()));
    }

    public Client getClient(Long clientId) {
        return Optional.ofNullable(clientId)
                .flatMap(clientRepository::findById)
                .orElse(null);
    }

    public Car getCar(Long carId) {
        return Optional.ofNullable(carId)
                .flatMap(carRepository::findById)
                .orElse(null);
    }
}
