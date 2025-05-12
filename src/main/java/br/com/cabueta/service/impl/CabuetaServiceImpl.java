package br.com.cabueta.service.impl;

import br.com.cabueta.entity.CabuetaClient;
import br.com.cabueta.entity.response.CabuetaClientResponse;
import br.com.cabueta.repository.CabuetaRepository;
import br.com.cabueta.service.CabuetaService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CabuetaServiceImpl implements CabuetaService {

    private final CabuetaRepository cabuetaRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public CabuetaClient save(CabuetaClient cabuetaClient) {
        cabuetaClient.setPassword(passwordEncoder.encode(cabuetaClient.getPassword()));
        Optional<CabuetaClientResponse> byCpfCnpj = cabuetaRepository.findByCpfCnpj(cabuetaClient.getCpfCnpj());
        if (!byCpfCnpj.isPresent()) {
            return cabuetaRepository.save(cabuetaClient);
        }
        throw new IllegalArgumentException("CPF já cadastrado");
    }

    @Override
    public Optional<CabuetaClient> findById(ObjectId id) {
        return cabuetaRepository.findById(id);
    }

    @Override
    public List<CabuetaClient> findAll() {
        return cabuetaRepository.findAll();
    }

    @Override
    public void delete(String cpfCnpj) {
        Optional<CabuetaClientResponse> byCpfCnpj = cabuetaRepository.findByCpfCnpj(cpfCnpj);
        if (byCpfCnpj.isPresent()) {
            cabuetaRepository.delete(CabuetaClient.builder().cpfCnpj(cpfCnpj).build());
        }

    }
}
