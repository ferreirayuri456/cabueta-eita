package br.com.cabueta.service;


import br.com.cabueta.entity.RegistrationClient;
import br.com.cabueta.entity.response.RegistrationClientResponse;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;


public interface RegistrationService {

    RegistrationClientResponse save(RegistrationClient cadastroClient);
    Optional<RegistrationClient> findById(ObjectId id);
    List<RegistrationClientResponse> findAll();
    void delete(String cpfCnpj);

}
