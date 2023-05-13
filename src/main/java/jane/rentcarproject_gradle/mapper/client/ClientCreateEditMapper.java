package jane.rentcarproject_gradle.mapper.client;

import jane.rentcarproject_gradle.database.entity.Client;
import jane.rentcarproject_gradle.database.entity.User;
import jane.rentcarproject_gradle.database.repository.UserRepository;
import jane.rentcarproject_gradle.dto.client.ClientCreateEditDto;
import jane.rentcarproject_gradle.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientCreateEditMapper implements Mapper<ClientCreateEditDto, Client> {
    private final UserRepository userRepository;

    @Override
    public Client map(ClientCreateEditDto fromObject, Client toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Client map(ClientCreateEditDto object) {
        Client client = new Client();
        copy(object, client);

        return client;
    }

    private void copy(ClientCreateEditDto object, Client client) {
        client.setBirthDate(object.getBirthDate());
        client.setDrivingLicenceNo(object.getDrivingLicenceNo());
        client.setValidity(object.getValidity());
        client.setUser(getUser(object.getUserId()));
    }

    public User getUser(Long userId) {
        return Optional.ofNullable(userId)
                .flatMap(userRepository::findById)
                .orElse(null);
    }
}
