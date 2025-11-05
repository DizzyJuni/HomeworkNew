package com.homework.Parking.controller;

import com.homework.Parking.dto.request.client.CreateClientRequest;
import com.homework.Parking.dto.request.client.UpdateClientRequest;
import com.homework.Parking.dto.response.client.ClientResponse;
import com.homework.Parking.dto.response.client.SimpleClientResponse;
import com.homework.Parking.entity.Client;
import com.homework.Parking.mapper.ClientMapper;
import com.homework.Parking.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper mapper;

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody @Valid CreateClientRequest request) {
        ClientResponse response = clientService.registerClient(request);
        return ResponseEntity.ok("Клиент успешно зарегистрирован.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable Long id) {
        ClientResponse response = clientService.getClientById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public List<ClientResponse> getAllClient() {
        return clientService.getAllClients();
    }

    @GetMapping("/by-phone/{phoneNumber}")
    public ResponseEntity<SimpleClientResponse> getClientByPhoneNumber(@PathVariable String phoneNumber) {
        SimpleClientResponse client = clientService.getClientByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/car/{Id}")
    public ResponseEntity<SimpleClientResponse> getClientByCarId(@PathVariable Long Id) {
        SimpleClientResponse response = clientService.getClientByCarId(Id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<String> updateClientById(@PathVariable Long clientId, @RequestBody @Valid UpdateClientRequest request) {
        clientService.updateClient(clientId, request);
        return ResponseEntity.ok("Клиент успешно изменен");
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClientById(@PathVariable Long clientId) {
        clientService.deleteClientById(clientId);
        return ResponseEntity.ok("Клиент успешно удален.");
    }
}
