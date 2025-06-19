package br.com.cabueta.controller;

import br.com.cabueta.controller.impl.ReportControllerImpl;
import br.com.cabueta.entity.ReportClient;
import br.com.cabueta.entity.request.ReportRequest;
import br.com.cabueta.entity.response.ReportResponse;
import br.com.cabueta.mapper.ReportMapper;
import br.com.cabueta.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReportControllerImplTest {

    @Mock
    private ReportService reportService;
    @Mock
    private ReportMapper reportMapper;

    @InjectMocks
    private ReportControllerImpl reportController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() throws IOException {
        ReportRequest reportRequest = ReportRequest.builder()
                .build();

        ReportResponse reportResponse = ReportResponse.builder()
                .build();

        when(reportService.save(reportRequest)).thenReturn(reportResponse);

        ResponseEntity<ReportResponse> responseEntity = reportController.save(reportRequest);

        assertEquals(ResponseEntity.ok(reportResponse), responseEntity);
        assertEquals(reportResponse, responseEntity.getBody());

        verify(reportService, times(1)).save(reportRequest);
    }

    @Test
    void testFindClientById(){
        List<ReportResponse> mockList = List.of(
                new ReportResponse("teste1", "afogados", 22.22, 50.50, "aaaah", LocalDateTime.now(), LocalDateTime.now()),
                new ReportResponse("teste2", "caxangá", 22.22, 50.50, "aaaah", LocalDateTime.now(), LocalDateTime.now()),
                new ReportResponse("teste3", "boa viagem", 22.22, 50.50, "aaaah", LocalDateTime.now(), LocalDateTime.now())
        );

        ReportClient reportClient = ReportClient.builder().build();

        when(reportService.findAllByClientId(anyLong())).thenReturn(List.of(reportClient));
        when(reportMapper.toResponse(reportClient)).thenReturn(any(ReportResponse.class));

        ResponseEntity<List<ReportResponse>> byClientId = reportController.findByClientId(1L);

        assertNotNull(byClientId);
        assertEquals(HttpStatus.OK, byClientId.getStatusCode());
        assertEquals(1, byClientId.getBody().size());
    }
}