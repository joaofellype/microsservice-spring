package com.pagamento.pix.core.domain.aggregates.pix;

import com.pagamento.pix.core.domain.aggregates.users.User;

public class Pix {


    private String id;
    private String idTransaction;
    private User userSend;
    private Long dateTransaction;
    private User userReceived;
    private String value;
    private String qrCode;
    private String status;


    private Pix(String id, String idTransaction, Long dateTransaction, String value,String qrCode) {
        this.id = id;
        this.idTransaction = idTransaction;
        this.dateTransaction = dateTransaction;
        this.value = value;
        this.qrCode = qrCode;
        this.status = "NO_AUTHORIZED";
    }


    public static Pix create(String id, String idTransaction, Long dateTransaction, String value, String qrCode) {
        var pix = new Pix(id, idTransaction, dateTransaction, value, qrCode);
        return pix;
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

    public User getUserSend() {
        return userSend;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getStatus() {
        return status;
    }

}

