package br.com.cabueta.controller;

import br.com.cabueta.entity.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


public interface AuthController {

    @CrossOrigin(origins = {
            "http://localhost:3000",
            "http://192.168.1.15:3000"
    })
    @PostMapping("")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest);

}
