package br.com.cabueta.service;


import br.com.cabueta.entity.CabuetaClient;
import br.com.cabueta.entity.response.CabuetaClientResponse;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CabuetaService {

    CabuetaClientResponse save(CabuetaClient cabuetaClient);
    Optional<CabuetaClient> findById(ObjectId id);
    List<CabuetaClientResponse> findAll();
    void delete(String cpfCnpj);

}
