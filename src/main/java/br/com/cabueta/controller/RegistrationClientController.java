package br.com.cabueta.controller;

import br.com.cabueta.entity.RegistrationClient;
import br.com.cabueta.entity.request.RegistrationClientRequest;
import br.com.cabueta.entity.response.RegistrationClientResponse;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RegistrationClientController {

    @PostMapping
    ResponseEntity<RegistrationClient> save(@RequestBody @Valid RegistrationClientRequest client);

    @GetMapping("/{id}")
    Optional<RegistrationClient> findById(@PathVariable ObjectId id);

    @GetMapping
    ResponseEntity<List<RegistrationClientResponse>> findAll();

    @DeleteMapping("/{id}")
    void deleteClient(@PathVariable String cpfCnpj);

    @PostMapping("/generate")
    ResponseEntity<Map<String, String>> generateClientId();
}
