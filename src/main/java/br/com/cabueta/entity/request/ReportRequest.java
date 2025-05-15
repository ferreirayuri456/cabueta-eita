package br.com.cabueta.entity.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportRequest {

    @NotBlank
    private String description;
    @NotBlank
    private String location;
    private MultipartFile imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
