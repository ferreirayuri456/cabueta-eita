package br.com.cabueta.controller.impl;

import br.com.cabueta.controller.CabuetaController;
import br.com.cabueta.entity.CabuetaClient;
import br.com.cabueta.entity.request.CabuetaClientRequest;
import br.com.cabueta.entity.response.CabuetaClientResponse;
import br.com.cabueta.service.CabuetaService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class CabuetaControllerImpl implements CabuetaController {

    @Autowired
    private final CabuetaService cabuetaService;

    @Override
    public ResponseEntity<CabuetaClient> save(CabuetaClientRequest request) {

        CabuetaClient cabuetaClient = CabuetaClient.builder()
                .name(request.getName())
                .email(request.getEmail())
                .cpfCnpj(request.getCpfCnpj())
                .password(request.getPassword())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        cabuetaService.save(cabuetaClient);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public Optional<CabuetaClient> findById(ObjectId id) {
        return cabuetaService.findById(id);
    }

    @Override
    public ResponseEntity<List<CabuetaClientResponse>> findAll() {
        return ResponseEntity.ok(cabuetaService.findAll());
    }

    @Override
    public void deleteClient(String cpfCnpj) {
        cabuetaService.delete(cpfCnpj);
    }
}
