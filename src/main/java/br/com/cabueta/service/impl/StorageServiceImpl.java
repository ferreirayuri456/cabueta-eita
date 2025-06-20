package br.com.cabueta.service.impl;

import br.com.cabueta.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {


    private final String uploadDir = "C:\\Users\\ferre\\OneDrive\\Desktop";

    @Override
    public String uploadFile(MultipartFile images) throws IOException {
        File file = new File(uploadDir + images.getOriginalFilename());
        images.transferTo(file);

        if (!file.exists()) {
            file.mkdirs(); // cria o diretório se não existir
        }

        return "Subindo as imagens na minha máquina local " + images.getOriginalFilename();
    }
}
