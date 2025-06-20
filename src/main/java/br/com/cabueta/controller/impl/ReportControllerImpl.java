package br.com.cabueta.controller.impl;

import br.com.cabueta.controller.ReportController;
import br.com.cabueta.entity.ReportClient;
import br.com.cabueta.entity.request.ReportRequest;
import br.com.cabueta.entity.response.ReportResponse;
import br.com.cabueta.mapper.ReportMapper;
import br.com.cabueta.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportControllerImpl implements ReportController {

    private final ReportService reportService;
    private final ReportMapper reportMapper;

    @Override
    public ResponseEntity<ReportResponse> save(ReportRequest request) throws IOException {
        return ResponseEntity.ok(reportService.save(request));
    }

    @Override
    public ResponseEntity<List<ReportResponse>> findByClientId(long id) {
        List<ReportClient> reports = reportService.findAllByClientId(id);
        List<ReportResponse> responseList = reports.stream()
                .map(reportMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responseList);
    }
}
