package br.com.cabueta.service.impl;

import br.com.cabueta.entity.CabuetaClient;
import br.com.cabueta.entity.response.CabuetaClientResponse;
import br.com.cabueta.mapper.ClientMapper;
import br.com.cabueta.repository.CabuetaRepository;
import br.com.cabueta.service.CabuetaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CabuetaServiceImpl implements CabuetaService {

    private final CabuetaRepository cabuetaRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientMapper clientMapper;


    @Override
    public CabuetaClientResponse save(CabuetaClient cabuetaClient) {
        cabuetaClient.setPassword(passwordEncoder.encode(cabuetaClient.getPassword()));
        Optional<CabuetaClientResponse> byCpfCnpj = cabuetaRepository.findByCpfCnpj(cabuetaClient.getCpfCnpj());
        if (!byCpfCnpj.isPresent()) {
            CabuetaClient saved = cabuetaRepository.save(cabuetaClient);
            return clientMapper.toResponse(saved);
        }
        throw new IllegalArgumentException("CPF já cadastrado");
    }

    @Override
    public Optional<CabuetaClient> findById(ObjectId id) {
        return cabuetaRepository.findById(id);
    }

    @Override
    public List<CabuetaClientResponse> findAll() {
        List<CabuetaClient> clients = cabuetaRepository.findAll();
        return clients.stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(String cpfCnpj) {
        Optional<CabuetaClientResponse> byCpfCnpj = cabuetaRepository.findByCpfCnpj(cpfCnpj);
        if (byCpfCnpj.isPresent()) {
            cabuetaRepository.delete(CabuetaClient.builder().cpfCnpj(cpfCnpj).build());
        }

    }
}
