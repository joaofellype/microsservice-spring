package com.micresservice.users.core.domain.service.users;

import com.micresservice.users.core.domain.aggregates.users.User;

public interface UserService {

    void create(String id, String name, String cpf);

    User findById(String id);
}
