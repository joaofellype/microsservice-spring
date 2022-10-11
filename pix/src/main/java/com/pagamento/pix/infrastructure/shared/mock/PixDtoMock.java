package com.pagamento.pix.infrastructure.shared.mock;

import com.pagamento.pix.application.pix.dto.PixDto;

public class PixDtoMock {

    public static PixDto create(){

        var pixDto = new PixDto();
        pixDto.setId("1234");
        pixDto.setQrCode("2579aa8d-5076-4c34-aab6-8dd5abc43a60");
        pixDto.setValue("120");
        pixDto.setIdTransaction("fccf6b01-640b-4e4f-86b7-0bcda5d7487a");
        pixDto.setDateTransaction(1665453150L);
        pixDto.setUserSend(UserRequestMock.create());
        pixDto.setUserReceived(UserRequestMock.create());

        return  pixDto;
    }
}
