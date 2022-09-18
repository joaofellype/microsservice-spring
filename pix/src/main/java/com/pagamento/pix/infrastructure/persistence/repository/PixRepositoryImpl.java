package com.pagamento.pix.infrastructure.persistence.repository;

import com.pagamento.pix.core.domain.aggregates.pix.Pix;
import com.pagamento.pix.core.domain.aggregates.pix.PixRepository;
import com.pagamento.pix.core.domain.shared.paginates.Paginate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pagamento.pix.infrastructure.persistence.mongo.QueryMongoDbHelper.*;

@Repository
public class PixRepositoryImpl implements PixRepository {

    private final MongoTemplate mongoTemplate;

    @Value("${spring.data.mongodb.collection}")
    private String defaultCollection;


    public PixRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private static void addDateAscSort(Query query) {
        addAscSort(query, "dateOfModification");
    }

    @Override
    public void save(Pix pix) {
        mongoTemplate.save(pix, defaultCollection);
    }

    @Override
    public List<Pix> getAll(Paginate paginate, String idUser) {
        Query query = new Query();
        addInCriteria(query, List.of(idUser), "userSend._id");
        paginate.setTotal(mongoTemplate.count(query, defaultCollection));
        addPaginateCriteria(query, paginate);
        return mongoTemplate.find(query, Pix.class, defaultCollection);
    }

    @Override
    public List<Pix> getAll(String numberTransaction, Paginate paginate) {
        Query query = new Query();
        paginate.setTotal(mongoTemplate.count(query, defaultCollection));
        addAndCriteria(query, numberTransaction, "idTransaction");
        addPaginateCriteria(query, paginate);

        return mongoTemplate.find(query, Pix.class, defaultCollection);
    }

    @Override
    public List<Pix> getAll(Paginate paginate) {

        Query query = new Query();
        paginate.setTotal(mongoTemplate.count(query, defaultCollection));
        addPaginateCriteria(query, paginate);

        return mongoTemplate.find(query, Pix.class, defaultCollection);
    }
    @Override
    public Pix findById(String id) {


        return mongoTemplate.findById(id, Pix.class, defaultCollection);
    }

    @Override
    public List<Pix> getPixByQrCode(String qrCode) {
        Query query = new Query();
        addAndCriteria(query, qrCode, "qrCode");

        return mongoTemplate.find(query,Pix.class,defaultCollection);
    }


}
