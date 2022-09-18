package com.pagamento.pix.core.domain.shared.paginates;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Paginate {


    @NotNull(message = "The paginate id is null")
    private String id;

    @NotNull(message = "the offset is null")
    @Min(value = 0, message = "the offset must be greater than or equal to zero")
    private Long offset;

    @NotNull(message = "the limit is null")
    @Min(value = 1, message = "The limit must be greater than 0")
    @Max(value = 1000, message = "The limit cannot be greater than 1000")
    private Integer limit;

    private Long total;

    private Paginate(String offset, String limit) {
        this.id = UUID.randomUUID().toString();
        this.offset = Long.parseLong(offset.trim());
        this.limit = getValidLimit(limit);
        this.total = 0L;
    }

    private Paginate(Long offset, Integer limit) {
        this.id = UUID.randomUUID().toString();
        this.offset = offset;
        this.limit = getValidLimit(limit);
        this.total = 0L;
    }

    public static Paginate create(Long offset, Integer limit) {
        var paginate = new Paginate(offset, limit);
        return paginate;
    }


    public static Paginate create(String offset, String limit) {
        var paginate = new Paginate(offset, limit);
        return paginate;
    }

    public static Integer getValidLimit(String limit) {
        return (limit != null) ? Integer.parseInt(limit.trim()) : 1000;
    }

    public static Integer getValidLimit(Integer limit) {
        return (limit != null) ? limit : 100;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }
}
