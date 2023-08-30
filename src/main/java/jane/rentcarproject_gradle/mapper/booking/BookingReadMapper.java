package jane.rentcarproject_gradle.mapper.booking;

import jane.rentcarproject_gradle.database.entity.Booking;
import jane.rentcarproject_gradle.dto.booking.BookingReadDto;
import jane.rentcarproject_gradle.dto.car.CarReadDto;
import jane.rentcarproject_gradle.dto.client.ClientReadDto;
import jane.rentcarproject_gradle.mapper.Mapper;
import jane.rentcarproject_gradle.mapper.car.CarReadMapper;
import jane.rentcarproject_gradle.mapper.client.ClientReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingReadMapper implements Mapper<Booking, BookingReadDto> {
    private final ClientReadMapper clientReadMapper;
    private final CarReadMapper carReadMapper;

    @Override
    public BookingReadDto map(Booking object) {
        ClientReadDto clientReadDto = Optional.ofNullable(object.getClient())
                .map(clientReadMapper::map)
                .orElse(null);

        CarReadDto carReadDto = Optional.ofNullable(object.getCar())
                .map(carReadMapper::map)
                .orElse(null);

        return new BookingReadDto(
                object.getId(),
                object.getRentalStart(),
                object.getRentalFinish(),
                object.getStatus(),
                object.getPaymentState(),
                object.getComment(),
                clientReadDto,
                carReadDto
        );
    }
}
