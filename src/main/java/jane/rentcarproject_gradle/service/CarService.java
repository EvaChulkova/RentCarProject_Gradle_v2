package jane.rentcarproject_gradle.service;

import jane.rentcarproject_gradle.database.repository.CarRepository;
import jane.rentcarproject_gradle.dto.car.CarCreateEditDto;
import jane.rentcarproject_gradle.dto.car.CarReadDto;
import jane.rentcarproject_gradle.mapper.car.CarCreateEditMapper;
import jane.rentcarproject_gradle.mapper.car.CarReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarService {
    private final CarRepository carRepository;
    private final CarReadMapper carReadMapper;
    private final CarCreateEditMapper carCreateEditMapper;


    public List<CarReadDto> findAll() {
        return carRepository.findAll().stream()
                .map(carReadMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<CarReadDto> findById(Long id) {
        return carRepository.findById(id)
                .map(carReadMapper::map);
    }

    @Transactional
    public CarReadDto create(CarCreateEditDto carCreateEditDto) {
        return Optional.ofNullable(carCreateEditDto)
                .map(carCreateEditMapper::map)
                .map(carRepository::save)
                .map(carReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<CarReadDto> update(Long id, CarCreateEditDto carCreateEditDto) {
        return carRepository.findById(id)
                .map(entity -> carCreateEditMapper.map(carCreateEditDto, entity))
                .map(carRepository::saveAndFlush)
                .map(carReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return carRepository.findById(id)
                .map(entity -> {carRepository.delete(entity);
                    carRepository.flush();
                    return true;
                })
                .orElse(false);
    }

}
