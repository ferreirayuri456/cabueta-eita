package br.com.cabueta.service.impl;

import br.com.cabueta.entity.ReportClient;
import br.com.cabueta.entity.request.ReportRequest;
import br.com.cabueta.entity.response.ReportResponse;
import br.com.cabueta.mapper.ReportMapper;
import br.com.cabueta.repository.ReportRepository;
import br.com.cabueta.service.ReportService;
import br.com.cabueta.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportMapper reportMapper;
    private final ReportRepository reportRepository;
    private final StorageService storageService;


    @Override
    public ReportResponse save(ReportRequest request) throws IOException {

        ReportClient client = reportMapper.toEntity(request);

        if (Objects.nonNull(request.getImageUrl())) {
            String uploadFile = storageService.uploadFile(request.getImageUrl());
            client.setImageUrl(uploadFile);
        }

        client.setReportId(generateRandomDEN());
        ReportClient reportClient = reportRepository.save(client);

        return reportMapper.toResponse(reportClient);
    }

    @Override
    public List<ReportResponse> findById(String id) {
        return reportRepository.findByReportId(id);
    }

    private String generateRandomDEN() {
        return "DEN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
