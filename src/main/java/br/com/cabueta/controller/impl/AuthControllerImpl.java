package br.com.cabueta.controller.impl;

import br.com.cabueta.controller.AuthController;
import br.com.cabueta.entity.RegistrationClient;
import br.com.cabueta.entity.request.LoginRequest;
import br.com.cabueta.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final RegistrationService registrationService;
    @Override
    public ResponseEntity<Map<String, Object>> login(LoginRequest loginRequest) {
        RegistrationClient optionalClient = registrationService.findByClientId(loginRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("clientId", optionalClient.getCpfCnpj());
        return ResponseEntity.ok(response);
    }
}
