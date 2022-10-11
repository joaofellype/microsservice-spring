package com.micresservice.users.core.domain.service.users;

import com.micresservice.users.core.domain.aggregates.users.User;
import com.micresservice.users.core.domain.aggregates.users.UserRepository;
import com.micresservice.users.core.domain.shared.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(String id, String name, String cpf) {

        var user = userRepository.findById(id);
        if (user == null)
            user = User.create(id, name, cpf);
        userRepository.save(user);
    }

    @Override
    public User findById(String id) {
        var user = userRepository.findById(id);
        if (user == null)
            throw new NotFoundException("User not found");
        return user;
    }

}
