package com.microsservice.bdcentral.core.domain.aggregates;

import java.time.LocalDateTime;

public class Pix {

    private String id;
    private String idTransaction;
    private User userSend;
    private Long dateTransaction;
    private User userReceived;
    private String value;
    private String qrCode;


    public Pix() {
    }

    public Pix(String id, String idTransaction, User userSend, Long dateTransaction, User userReceived, String value, String qrCode) {
        this.id = id;
        this.idTransaction = idTransaction;
        this.userSend = userSend;
        this.dateTransaction = dateTransaction;
        this.userReceived = userReceived;
        this.value = value;
        this.qrCode = qrCode;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public void setDateTransaction(Long dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public void setValue(String value) {
        this.value = value;
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

    public void setUserSend(User userSend) {
        this.userSend = userSend;
    }

    public User getUserReceived() {
        return userReceived;
    }

    public void setUserReceived(User userReceived) {
        this.userReceived = userReceived;
    }
}
