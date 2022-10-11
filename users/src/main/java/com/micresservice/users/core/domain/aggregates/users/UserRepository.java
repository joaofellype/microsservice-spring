package com.micresservice.users.core.domain.aggregates.users;

public interface UserRepository {

    void save(User user);

    User findById(String id);

}
