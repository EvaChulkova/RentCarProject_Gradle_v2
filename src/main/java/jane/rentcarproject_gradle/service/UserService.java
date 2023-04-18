package jane.rentcarproject_gradle.service;

import com.querydsl.core.types.Predicate;
import jane.rentcarproject_gradle.database.repository.UserRepository;
import jane.rentcarproject_gradle.dto.user.UserCreateEditDto;
import jane.rentcarproject_gradle.dto.user.UserReadDto;
import jane.rentcarproject_gradle.mapper.user.UserCreateEditMapper;
import jane.rentcarproject_gradle.mapper.user.UserReadMapper;
import jane.rentcarproject_gradle.query.QPredicate;
import jane.rentcarproject_gradle.query.filter.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static jane.rentcarproject_gradle.database.entity.QUser.user;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public Page<UserReadDto> findAll(UserFilter userFilter, Pageable pageable) {
        Predicate predicate = QPredicate.builder()
                .add(userFilter.getFirstName(), user.firstName::containsIgnoreCase)
                .add(userFilter.getLastname(), user.lastName::containsIgnoreCase)
                .add(userFilter.getLogin(), user.login::containsIgnoreCase)
                .buildAnd();

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::map);
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userCreateEditDto) {
        return Optional.ofNullable(userCreateEditDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }


    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userCreateEditDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userCreateEditDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
