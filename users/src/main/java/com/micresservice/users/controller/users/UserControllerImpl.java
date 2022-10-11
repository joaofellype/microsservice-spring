package com.micresservice.users.controller.users;

import com.micresservice.users.application.dto.UserDto;
import com.micresservice.users.application.save.UserRequest;
import com.micresservice.users.controller.shared.BaseControllerImpl;
import com.micresservice.users.core.domain.service.users.UserService;
import com.micresservice.users.core.queries.GetUsersQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl extends BaseControllerImpl<UserDto> implements UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private GetUsersQuery getUsersQuery;

    @Override
    public ResponseEntity<Void> create(UserRequest userRequest) {
        userService.create(userRequest.getId(), userRequest.getName(), userRequest.getCpf());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserDto> findById(String id) {
        var user = userService.findById(id);
        if (user == null) {
            return notFoundAggregate();
        } else {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setCpf(user.getCpf());
            userDto.setName(user.getName());

            return ResponseEntity.ok(userDto);
        }
    }
}
