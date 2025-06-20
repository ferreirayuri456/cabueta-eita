package br.com.cabueta.service.impl;

import br.com.cabueta.entity.RegistrationClient;
import br.com.cabueta.entity.request.LoginRequest;
import br.com.cabueta.entity.response.RegistrationClientResponse;
import br.com.cabueta.mapper.ClientMapper;
import br.com.cabueta.repository.RegistrationRepository;
import br.com.cabueta.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository cadastroRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientMapper clientMapper;


    @Override
    public RegistrationClientResponse save(RegistrationClient cadastroClient) {
        cadastroClient.setPassword(passwordEncoder.encode(cadastroClient.getPassword()));
        Optional<RegistrationClientResponse> byCpfCnpj = cadastroRepository.findByCpfCnpj(cadastroClient.getCpfCnpj());
        if (!byCpfCnpj.isPresent()) {
            RegistrationClient saved = cadastroRepository.save(cadastroClient);
            return clientMapper.toResponse(saved);
        }
        throw new IllegalArgumentException("CPF já cadastrado");
    }

    @Override
    public Optional<RegistrationClient> findById(ObjectId id) {
        return cadastroRepository.findById(id);
    }

    @Override
    public List<RegistrationClientResponse> findAll() {
        List<RegistrationClient> clients = cadastroRepository.findAll();
        return clients.stream().map(clientMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(String cpfCnpj) {
        Optional<RegistrationClientResponse> byCpfCnpj = cadastroRepository.findByCpfCnpj(cpfCnpj);
        if (byCpfCnpj.isPresent()) {
            cadastroRepository.delete(RegistrationClient.builder().cpfCnpj(cpfCnpj).build());
        }

    }

    @Override
    public RegistrationClient findByClientId(LoginRequest loginRequest) {
        Optional<RegistrationClientResponse> optionalClient = cadastroRepository.findByCpfCnpj(loginRequest.getCpfCnpj());

        RegistrationClientResponse client = optionalClient.orElseGet(() -> {
            RegistrationClientResponse newClient = new RegistrationClientResponse();
            newClient.setCpfCnpj(loginRequest.getCpfCnpj());

            return newClient;
        });
        return cadastroRepository.save(clientMapper.toEntity(client));
    }
}
