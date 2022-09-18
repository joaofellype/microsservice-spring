package com.pagamento.pix.core.command.pix.save;

import com.pagamento.pix.application.pix.dto.PixDto;
import com.pagamento.pix.application.pix.save.PixRequest;
import com.pagamento.pix.application.users.save.UserRequest;
import com.pagamento.pix.controller.users.UserController;
import com.pagamento.pix.core.domain.aggregates.pix.Pix;
import com.pagamento.pix.core.domain.aggregates.pix.PixRepository;
import com.pagamento.pix.core.domain.aggregates.users.User;
import com.pagamento.pix.core.domain.service.pix.PixService;
import com.pagamento.pix.core.domain.service.users.CreateUserDomainService;
import com.pagamento.pix.core.domain.shared.exception.NotFoundException;
import com.pagamento.pix.core.domain.shared.helpers.GenerateString;
import com.pagamento.pix.core.domain.shared.helpers.ObjectMapperJson;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class PixCommandHandler {

    private final PixService pixService;
    private final PixRepository pixRepository;
    private final CreateUserDomainService createUserDomainService;
    private final PixProducer pixProducer;
    @Autowired
    private final DozerBeanMapper mapper;
    @Autowired
    private UserController userController;

    @Autowired
    public PixCommandHandler(PixService pixService, PixRepository pixRepository, CreateUserDomainService createUserDomainService, PixProducer pixProducer,DozerBeanMapper dozerBeanMapper) {
        this.pixService = pixService;
        this.pixRepository = pixRepository;
        this.createUserDomainService = createUserDomainService;
        this.pixProducer = pixProducer;
        this.mapper = dozerBeanMapper;


    }

    public void handle(PixRequest pixRequest) {

        var userSend = exceptionUser(pixRequest, "send");
        var userReceived = exceptionUser(pixRequest, "received");
        pixRequest.setUserSend(userSend);
        pixRequest.setUserReceived(userReceived);
        var pix = pixService.execute(pixRequest.getId(), UUID.randomUUID().toString(), LocalDateTime.now(), pixRequest.getValue(), GenerateString.getRandomString(30));
        pix.setUserReceived(setUserReceived(pixRequest.getUserReceived()));
        pix.setUserSend(setUserSend(pixRequest.getUserReceived()));
        pixRepository.save(pix);
        sendMessage(pix);
    }

    public PixDto authorizeQrCode(String qrCode){

        var pix = pixService.execute(qrCode);
        pix.setStatus("AUTHORIZED");
        pixRepository.save(pix);
        PixDto pixDto = mapper.map(pix,PixDto.class);
        return pixDto;
    }

    private void sendMessage(Pix pix){
        String json = ObjectMapperJson.toStringJson(pix);
        pixProducer.send(json);
    }
    private User setUserReceived(UserRequest userRequest) {
        return createUserDomainService.create(userRequest.getId(), userRequest.getName(), userRequest.getCpf());
    }

    private User setUserSend(UserRequest userRequest) {

        return createUserDomainService.create(userRequest.getId(), userRequest.getName(), userRequest.getCpf());
    }

    private UserRequest exceptionUser(PixRequest pixRequest, String typeUser) {
        UserRequest userSend = new UserRequest();
        var message = "User " + typeUser + " not Found";
        try {
            ResponseEntity<UserRequest> userRequest;
            if (typeUser.equals("send")) {
                userRequest = userController.findById(pixRequest.getIdUserSend());
            } else {
                userRequest = userController.findById(pixRequest.getIdUserReceived());
            }
            userSend = userRequest.getBody();

        } catch (Exception e) {
            if (e.getMessage().contains("NOT_FOUND")) {
                throw new NotFoundException(message);
            }
        }
        return userSend;
    }
}
