package br.com.cabueta.repository;

import br.com.cabueta.entity.RegistrationClient;
import br.com.cabueta.entity.response.RegistrationClientResponse;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends MongoRepository<RegistrationClient, ObjectId> {

    Optional<RegistrationClientResponse> findByCpfCnpj(String cpfCnpj);
}
