package com.pagamento.pix.application.pix.dto;

import com.pagamento.pix.application.shared.PaginateDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PixFilterDto {

    private PaginateDto paginate;
    private String numberTransaction;
    private String idUser;


    public PixFilterDto(PaginateDto paginateDto, String idUser) {
        this.paginate = paginateDto;
        this.idUser = idUser;
    }

    public PixFilterDto(String numberTransaction) {
        this.numberTransaction = numberTransaction;
    }

    public PixFilterDto(PaginateDto paginateDto) {
        this.paginate = paginateDto;
    }

    public PaginateDto getPaginate() {
        return paginate;
    }

    public void setPaginate(PaginateDto paginateDto) {
        this.paginate = paginateDto;
    }

    public String getNumberTransaction() {
        return numberTransaction;
    }

    public void setNumberTransaction(String numberTransaction) {
        this.numberTransaction = numberTransaction;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
