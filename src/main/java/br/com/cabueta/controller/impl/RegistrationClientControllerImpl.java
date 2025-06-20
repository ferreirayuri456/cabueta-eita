package br.com.cabueta.controller.impl;

import br.com.cabueta.controller.RegistrationClientController;
import br.com.cabueta.entity.RegistrationClient;
import br.com.cabueta.entity.request.RegistrationClientRequest;
import br.com.cabueta.entity.response.RegistrationClientResponse;
import br.com.cabueta.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class RegistrationClientControllerImpl implements RegistrationClientController {

    @Autowired
    private final RegistrationService cadastroService;

    @Override
    public ResponseEntity<RegistrationClient> save(RegistrationClientRequest request) {

        RegistrationClient cadastroClient = RegistrationClient.builder()
                .name(request.getName())
                .email(request.getEmail())
                .cpfCnpj(request.getCpfCnpj())
                .password(request.getPassword())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        cadastroService.save(cadastroClient);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public Optional<RegistrationClient> findById(ObjectId id) {
        return cadastroService.findById(id);
    }

    @Override
    public ResponseEntity<List<RegistrationClientResponse>> findAll() {
        return ResponseEntity.ok(cadastroService.findAll());
    }

    @Override
    public void deleteClient(String cpfCnpj) {
        cadastroService.delete(cpfCnpj);
    }

    @Override
    public ResponseEntity<Map<String, String>> generateClientId() {

        String clientId = UUID.randomUUID().toString();

        Map<String, String> response = new HashMap<>();
        response.put("clientId", clientId);

        return ResponseEntity.ok(response);
    }
}
