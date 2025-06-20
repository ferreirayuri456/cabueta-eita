package br.com.cabueta.entity.response;

import br.com.cabueta.entity.RegistrationClient;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationClientResponse {


    @Field("name")
    private String clientName;
    private String cpfCnpj;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
