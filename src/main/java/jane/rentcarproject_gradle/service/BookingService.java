package jane.rentcarproject_gradle.service;

import jane.rentcarproject_gradle.database.repository.BookingRepository;
import jane.rentcarproject_gradle.dto.booking.BookingCreateEditDto;
import jane.rentcarproject_gradle.dto.booking.BookingReadDto;
import jane.rentcarproject_gradle.mapper.booking.BookingCreateEditMapper;
import jane.rentcarproject_gradle.mapper.booking.BookingReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingReadMapper bookingReadMapper;
    private final BookingCreateEditMapper bookingCreateEditMapper;

    public List<BookingReadDto> findAll() {
        return bookingRepository.findAll().stream()
                .map(bookingReadMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<BookingReadDto> findById(Long id) {
        return bookingRepository.findById(id)
                .map(bookingReadMapper::map);
    }

    @Transactional
    public BookingReadDto create(BookingCreateEditDto bookingCreateEditDto) {
        return Optional.ofNullable(bookingCreateEditDto)
                .map(bookingCreateEditMapper::map)
                .map(bookingRepository::save)
                .map(bookingReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<BookingReadDto> update(Long id, BookingCreateEditDto bookingCreateEditDto) {
        return bookingRepository.findById(id)
                .map(entity -> bookingCreateEditMapper.map(bookingCreateEditDto, entity))
                .map(bookingRepository::saveAndFlush)
                .map(bookingReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return bookingRepository.findById(id)
                .map(entity -> {
                    bookingRepository.delete(entity);
                    bookingRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
