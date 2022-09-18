package com.pagamento.pix.core.domain.service.users;

import com.pagamento.pix.core.domain.aggregates.users.User;
import org.springframework.stereotype.Service;

@Service
public class CreateUserDomainServiceImpl implements CreateUserDomainService {
    @Override
    public User create(String id, String name, String cpf) {


        return User.create(id, name, cpf);
    }
}
