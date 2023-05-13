package jane.rentcarproject_gradle.service;

import jane.rentcarproject_gradle.database.repository.ClientRepository;
import jane.rentcarproject_gradle.database.repository.UserRepository;
import jane.rentcarproject_gradle.dto.client.ClientCreateEditDto;
import jane.rentcarproject_gradle.dto.client.ClientReadDto;
import jane.rentcarproject_gradle.mapper.client.ClientCreateEditMapper;
import jane.rentcarproject_gradle.mapper.client.ClientReadMapper;
import jane.rentcarproject_gradle.mapper.user.UserCreateEditMapper;
import jane.rentcarproject_gradle.mapper.user.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    private final ClientRepository clientRepository;
    private final ClientReadMapper clientReadMapper;
    private final ClientCreateEditMapper clientCreateEditMapper;


    public List<ClientReadDto> findAll() {
        return clientRepository.findAll().stream()
                .map(clientReadMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<ClientReadDto> findById(Long id) {
        return clientRepository.findById(id)
                .map(clientReadMapper::map);
    }

    public Optional<ClientReadDto> findClientByUserId(Long id) {
        return clientRepository.findByUserId(id)
                .map(clientReadMapper::map);
    }

    @Transactional
    public ClientReadDto create(ClientCreateEditDto clientCreateEditDto) {
        return Optional.ofNullable(clientCreateEditDto)
                .map(clientCreateEditMapper::map)
                .map(clientRepository::save)
                .map(clientReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ClientReadDto> update(Long id, ClientCreateEditDto clientCreateEditDto) {
        return clientRepository.findById(id)
                .map(entity -> clientCreateEditMapper.map(clientCreateEditDto, entity))
                .map(clientRepository::saveAndFlush)
                .map(clientReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return clientRepository.findById(id)
                .map(entity -> {
                    clientRepository.delete(entity);
                    clientRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
