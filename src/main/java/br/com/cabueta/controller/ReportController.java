package br.com.cabueta.controller;

import br.com.cabueta.entity.request.ReportRequest;
import br.com.cabueta.entity.response.ReportResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

public interface ReportController {

    @CrossOrigin(origins = {
            "http://localhost:3000",
            "http://192.168.1.15:3000"
    })    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ReportResponse> save(@ModelAttribute ReportRequest request) throws IOException;
    @CrossOrigin(origins = {
            "http://localhost:3000",
            "http://192.168.1.15:3000"
    })    @GetMapping("/{id}")
    ResponseEntity<List<ReportResponse>> findByClientId(@PathVariable long id);

}
