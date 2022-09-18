package com.pagamento.pix.application.shared;

public class PaginateDto {

    private String offset;
    private String limit;
    private String total;

    public PaginateDto(String offset, String limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public String getOffset() {
        return offset;
    }

    public String getLimit() {
        return limit;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


}
