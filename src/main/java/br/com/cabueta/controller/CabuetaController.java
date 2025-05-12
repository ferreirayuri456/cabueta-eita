package br.com.cabueta.controller;

import br.com.cabueta.entity.CabuetaClient;
import br.com.cabueta.entity.request.CabuetaClientRequest;
import br.com.cabueta.entity.response.CabuetaClientResponse;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public interface CabuetaController {

    @PostMapping
    ResponseEntity<CabuetaClient> save(@RequestBody @Validated CabuetaClientRequest client);

    @GetMapping("/{id}")
    Optional<CabuetaClient> findById(@PathVariable ObjectId id);

    @GetMapping
    Iterable<CabuetaClient> findAll();

    @DeleteMapping("/{id}")
    void deleteClient(@PathVariable String cpfCnpj);
}
