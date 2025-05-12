package br.com.cabueta.repository;

import br.com.cabueta.entity.CabuetaClient;
import br.com.cabueta.entity.response.CabuetaClientResponse;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CabuetaRepository extends MongoRepository<CabuetaClient, ObjectId> {

    Optional<CabuetaClientResponse> findByCpfCnpj(String cpfCnpj);
}
