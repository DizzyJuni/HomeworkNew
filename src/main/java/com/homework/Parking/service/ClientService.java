package com.homework.Parking.service;

import com.homework.Parking.dto.request.client.CreateClientRequest;
import com.homework.Parking.dto.request.client.UpdateClientRequest;
import com.homework.Parking.dto.response.client.ClientResponse;
import com.homework.Parking.dto.response.client.SimpleClientResponse;
import com.homework.Parking.entity.Client;
import com.homework.Parking.exception.ClientNotFoundException;
import com.homework.Parking.exception.ValidationException;
import com.homework.Parking.mapper.ClientMapper;
import com.homework.Parking.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;

    public ClientResponse registerClient(CreateClientRequest request) {
        Client client = mapper.toEntity(request);
        if (client.getAge() < 18) {
            throw new ValidationException("Минимальный возраст 18 лет.");
        }
        if (clientRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new ValidationException("Номер телефона уже зарегистрирован.");
        }
        if (clientRepository.existsByEmail(request.getEmail())) {
            throw new ValidationException("Почта уже зарегистрирована.");
        }
        clientRepository.save(client);
        return mapper.toResponse(client);
    }

    public ClientResponse getClientById(Long client_id) {
        Client client = clientRepository.findById(client_id)
                .orElseThrow(() -> new ClientNotFoundException("Клиент не найден"));
        return mapper.toResponse(client);
    }

    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public SimpleClientResponse getClientByPhoneNumber(String phoneNumber) {
        Client client = clientRepository.findByPhoneNumber(phoneNumber);
        return mapper.toResponseSimple(client);
    }

    public SimpleClientResponse getClientByCarId(Long id) {
        return clientRepository.findByCarsId(id)
                .map(mapper::toResponseSimple)
                .orElseThrow(() -> new ClientNotFoundException("Клиент с авто ID " + id + " не найден"));
    }

    public ClientResponse updateClient(Long id, UpdateClientRequest updateClientRequest) {
        Client currentClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Клиент не найден"));

        if (!updateClientRequest.getEmail().equals(currentClient.getEmail()) &&
                clientRepository.existsByEmail(updateClientRequest.getEmail())) {
            throw new ValidationException("Email уже зарегистрирован.");
        }
        if (!updateClientRequest.getPhoneNumber().equals(currentClient.getPhoneNumber()) &&
                clientRepository.existsByPhoneNumber(updateClientRequest.getPhoneNumber())) {
            throw new ValidationException("Номер телефона уже зарегистрирован");
        }

        currentClient.setName(updateClientRequest.getName());
        currentClient.setAge(updateClientRequest.getAge());
        currentClient.setPhoneNumber(updateClientRequest.getPhoneNumber());
        currentClient.setEmail(updateClientRequest.getEmail());

        return mapper.toResponse(clientRepository.save(currentClient));
    }

    public void deleteClientById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Клиент не найден");
        }

        clientRepository.deleteById(id);
    }
}
