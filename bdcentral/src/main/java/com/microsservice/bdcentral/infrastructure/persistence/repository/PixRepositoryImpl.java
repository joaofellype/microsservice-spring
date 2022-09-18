package com.microsservice.bdcentral.infrastructure.persistence.repository;

import com.microsservice.bdcentral.core.domain.aggregates.Pix;
import com.microsservice.bdcentral.core.domain.service.PixRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PixRepositoryImpl implements PixRepository {

    private final MongoTemplate mongoTemplate;

    @Value("${spring.data.mongodb.collection}")
    private String defaultCollection;

    public PixRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void save(Pix pix) {
        mongoTemplate.save(pix, defaultCollection);
    }

    public Pix findById(String id) {
        return mongoTemplate.findById(id, Pix.class, defaultCollection);
    }

    public List<Pix> findAll() {
        return mongoTemplate.findAll(Pix.class, defaultCollection);
    }
}
