package com.pagamento.pix.application.pix.dto;

import com.pagamento.pix.application.shared.PaginateDto;

public class GetPixQuery {

    private PixFilterDto filter;

    public GetPixQuery(PaginateDto paginateDto, String idUser) {
        this.filter = new PixFilterDto(paginateDto, idUser);
    }

    public GetPixQuery(String numberTransaction) {
        this.filter = new PixFilterDto(numberTransaction);
    }

    public GetPixQuery(String idUser, String offset, String limit) {
        this.filter = new PixFilterDto(new PaginateDto(offset, limit),idUser);
        this.filter.setPaginate(new PaginateDto(offset, limit));
    }

    public GetPixQuery(String offset, String limit) {
        this.filter = new PixFilterDto(new PaginateDto(offset, limit));
    }

    public String getUser() {
        return this.filter.getIdUser();
    }

    public String getNumberTransaction() {
        return this.filter.getNumberTransaction();
    }

    public PixFilterDto getFilter() {
        return filter;
    }

    public PaginateDto getPaginate() {
        return filter.getPaginate();
    }

    public void setPaginate(PaginateDto paginateDto) {
        filter.setPaginate(paginateDto);
    }
}
