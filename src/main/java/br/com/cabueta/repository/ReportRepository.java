package br.com.cabueta.repository;

import br.com.cabueta.entity.ReportClient;
import br.com.cabueta.entity.response.ReportResponse;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends MongoRepository<ReportClient, ObjectId> {

    List<ReportResponse> findByReportId(String clientId);
    List<ReportClient> findByClientId(long id);


}
