package br.com.cabueta.controller;

import br.com.cabueta.entity.CabuetaClient;
import br.com.cabueta.entity.request.CabuetaClientRequest;
import br.com.cabueta.entity.response.CabuetaClientResponse;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface CabuetaController {

    @PostMapping
    ResponseEntity<CabuetaClient> save(@RequestBody @Valid CabuetaClientRequest client);

    @GetMapping("/{id}")
    Optional<CabuetaClient> findById(@PathVariable ObjectId id);

    @GetMapping
    ResponseEntity<List<CabuetaClientResponse>> findAll();

    @DeleteMapping("/{id}")
    void deleteClient(@PathVariable String cpfCnpj);
}
