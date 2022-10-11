package com.microsservice.bdcentral.core.domain.aggregates;

import java.time.LocalDateTime;

public class Pix {

    private String id;
    private final String idTransaction;
    private final User userSend;
    private final Long dateTransaction;
    private final User userReceived;
    private final String value;
    private String qrCode;



    private Pix(String id, String idTransaction, User userSend, Long dateTransaction, User userReceived, String value, String qrCode) {
        this.id = id;
        this.idTransaction = idTransaction;
        this.userSend = userSend;
        this.dateTransaction = dateTransaction;
        this.userReceived = userReceived;
        this.value = value;
        this.qrCode = qrCode;
    }
    public static Pix create(String id, String idTransaction, User userSend, Long dateTransaction, User userReceived, String value, String qrCode){

        return new Pix(id, idTransaction, userSend, dateTransaction, userReceived, value, qrCode);
    }
    public String getId() {
        return id;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public Long getDateTransaction() {
        return dateTransaction;
    }

    public String getValue() {
        return value;
    }

    public User getUserSend() {
        return userSend;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }


    public User getUserReceived() {
        return userReceived;
    }
}
