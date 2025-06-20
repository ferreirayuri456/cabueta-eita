package br.com.cabueta.controller;

import br.com.cabueta.controller.impl.RegistrationClientControllerImpl;
import br.com.cabueta.entity.RegistrationClient;
import br.com.cabueta.entity.request.RegistrationClientRequest;
import br.com.cabueta.entity.response.RegistrationClientResponse;
import br.com.cabueta.service.RegistrationService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RegistrationClientControllerImplTest {


    @Mock //Vai mockar o comportamento(métodos e lógica) da classe
    private RegistrationService cadastroService;

    @InjectMocks //@InjectMocks injeta o mock no controller e ignora o @Autowired do código real.
    private RegistrationClientControllerImpl controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_ReturnsCreatedStatus() {
        RegistrationClientRequest request = new RegistrationClientRequest();
        request.setName("João");
        request.setEmail("joao@example.com");
        request.setCpfCnpj("12345678901");
        request.setPassword("senha123");
        request.setCreatedAt(LocalDateTime.now());


        ResponseEntity<RegistrationClient> response = controller.save(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(cadastroService, times(1)).save(any(RegistrationClient.class));
    }

    @Test
    void testFindById_ReturnsClient() {
        ObjectId id = new ObjectId();
        RegistrationClient client = new RegistrationClient();
        client.setId(id);
        when(cadastroService.findById(id)).thenReturn(Optional.of(client));

        Optional<RegistrationClient> result = controller.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(cadastroService, times(1)).findById(id);
    }

    @Test
    void testFindAll_ReturnsList() {
        List<RegistrationClientResponse> mockList = List.of(
                new RegistrationClientResponse("João", "12365478898", "joao@example.com", "testepassword", LocalDateTime.now(), LocalDateTime.now()),
                new RegistrationClientResponse("Maria", "12365478898", "maria@example.com", "testepassword", LocalDateTime.now(), LocalDateTime.now()),
                new RegistrationClientResponse("Gustavo", "12365478898", "maria@example.com", "testepassword", LocalDateTime.now(), LocalDateTime.now())
        );

        when(cadastroService.findAll()).thenReturn(mockList);

        ResponseEntity<List<RegistrationClientResponse>> response = controller.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().size());
        verify(cadastroService, times(1)).findAll();
    }

    @Test
    void testDeleteClient_CallsServiceDelete() {
        String cpfCnpj = "12345678901";

        controller.deleteClient(cpfCnpj);

        verify(cadastroService, times(1)).delete(cpfCnpj);
    }
}
