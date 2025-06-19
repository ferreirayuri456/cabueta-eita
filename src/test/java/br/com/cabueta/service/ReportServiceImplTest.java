package br.com.cabueta.service;

import br.com.cabueta.entity.ReportClient;
import br.com.cabueta.entity.request.ReportRequest;
import br.com.cabueta.entity.response.ReportResponse;
import br.com.cabueta.mapper.ReportMapper;
import br.com.cabueta.repository.ReportRepository;
import br.com.cabueta.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        // Arrange (prepara os dados)
        ReportRequest request = ReportRequest.builder()
                .imageUrl(new MockMultipartFile("image", new byte[0])) // se o seu objeto tiver image
                .build();

        ReportClient client = ReportClient.builder().build();

        when(reportMapper.toEntity(request)).thenReturn(client);
        when(storageService.uploadFile(any())).thenReturn("https://fake-url.com/image.jpg");
        when(reportRepository.save(any())).thenReturn(client);

        // Act (executa o método que você quer testar)
        ReportResponse savedClient = reportService.save(request);

        // Assert / Verify (verifica que os mocks foram chamados corretamente)
        verify(reportMapper, times(1)).toEntity(request);
        verify(storageService, times(1)).uploadFile(any());
        verify(reportRepository, times(1)).save(any());
    }

}
