package com.pagamento.pix.infrastructure.shared.mock;

import com.pagamento.pix.application.users.save.UserRequest;

public class UserRequestMock {

    public static UserRequest create(){

        var userRequest = new UserRequest();
        userRequest.setName("Joao");
        userRequest.setId("123");
        userRequest.setCpf("123456");
        return userRequest;
    }
}
