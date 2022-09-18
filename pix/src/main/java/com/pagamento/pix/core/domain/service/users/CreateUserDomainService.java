package com.pagamento.pix.core.domain.service.users;

import com.pagamento.pix.core.domain.aggregates.users.User;

public interface CreateUserDomainService {

    User create(String id, String name, String cpf);
}
