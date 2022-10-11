package com.micresservice.users.infrasctruture.persistence.repository;

import com.micresservice.users.core.domain.aggregates.users.User;
import com.micresservice.users.core.domain.aggregates.users.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoyImpl implements UserRepository {

    private final MongoTemplate mongoTemplate;

    @Value("${spring.data.mongodb.collection}")
    private String defaultCollection;

    public UserRepositoyImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public User findById(String id) {
        return mongoTemplate.findById(id, User.class, defaultCollection);
    }
}
