package br.com.cabueta.service;

import br.com.cabueta.entity.ReportClient;
import br.com.cabueta.entity.request.ReportRequest;
import br.com.cabueta.mapper.ReportMapper;
import br.com.cabueta.repository.ReportRepository;
import br.com.cabueta.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReportServiceImplTest {

    @Mock
    private ReportMapper reportMapper;
    @Mock
    private ReportRepository reportRepository;
    @Mock
    private StorageService storageService;
    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @InjectMocks
    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() throws IOException {
        ReportRequest request = ReportRequest.builder()
                .build();

        ReportClient client = reportMapper.toEntity(request);

        when(storageService.uploadFile(any())).thenReturn("");
        when(sequenceGeneratorService.generateSequence(any())).thenReturn(1L);
        when(reportRepository.save(any())).thenReturn(client);

        verify(reportMapper, times(1)).toEntity(request);
        verify(storageService, times(1)).uploadFile(any());




    }
}
