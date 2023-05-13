package jane.rentcarproject_gradle.mapper.client;

import jane.rentcarproject_gradle.database.entity.Client;
import jane.rentcarproject_gradle.dto.client.ClientReadDto;
import jane.rentcarproject_gradle.dto.user.UserReadDto;
import jane.rentcarproject_gradle.mapper.Mapper;
import jane.rentcarproject_gradle.mapper.user.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientReadMapper implements Mapper<Client, ClientReadDto> {
    private final UserReadMapper userReadMapper;

    @Override
    public ClientReadDto map(Client object) {
        UserReadDto userReadDto = Optional.ofNullable(object.getUser())
                .map(userReadMapper::map)
                .orElse(null);

        return new ClientReadDto(
                object.getId(),
                object.getBirthDate(),
                object.getDrivingLicenceNo(),
                object.getValidity(),
                userReadDto
        );
    }
}
