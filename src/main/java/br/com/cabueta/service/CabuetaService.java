package br.com.cabueta.service;


import br.com.cabueta.entity.CabuetaClient;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CabuetaService {

    CabuetaClient save(CabuetaClient cabuetaClient);
    Optional<CabuetaClient> findById(ObjectId id);
    List<CabuetaClient> findAll();
    void delete(String cpfCnpj);

}
