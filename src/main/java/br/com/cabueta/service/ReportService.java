package br.com.cabueta.service;

import br.com.cabueta.entity.ReportClient;
import br.com.cabueta.entity.request.ReportRequest;
import br.com.cabueta.entity.response.ReportResponse;

import java.io.IOException;
import java.util.List;

public interface ReportService {

    ReportResponse save (ReportRequest request) throws IOException;
    List<ReportClient> findAllByClientId(long id);
}
