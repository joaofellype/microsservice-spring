package com.pagamento.pix.application.pix.dto;

import com.pagamento.pix.infrastructure.shared.mock.PixDtoMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PixDtoTest {

    @Test
    void shouldObjectNotNull(){
        var pixDtoMock = PixDtoMock.create();
        assertNotNull(pixDtoMock);
        assertNotNull(pixDtoMock.getUserReceived());
        assertNotNull(pixDtoMock.getUserSend());
    }
    @Test
    void shouldObjectComparasion(){
        var pixDtoMock = PixDtoMock.create();

        assertEquals(pixDtoMock.getId(),"1234");
        assertEquals(pixDtoMock.getQrCode(),"2579aa8d-5076-4c34-aab6-8dd5abc43a60");
        assertEquals(pixDtoMock.getValue(),"120");
        assertEquals(pixDtoMock.getIdTransaction(),"fccf6b01-640b-4e4f-86b7-0bcda5d7487a");
        assertEquals(pixDtoMock.getDateTransaction(),1665453150L);

    }
}