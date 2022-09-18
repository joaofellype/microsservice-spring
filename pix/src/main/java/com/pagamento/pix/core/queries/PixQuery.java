package com.pagamento.pix.core.queries;

import com.pagamento.pix.application.pix.dto.GetPixQuery;
import com.pagamento.pix.application.pix.dto.PixDto;
import com.pagamento.pix.application.shared.PaginateDto;
import com.pagamento.pix.core.domain.aggregates.pix.Pix;
import com.pagamento.pix.core.domain.aggregates.pix.PixRepository;
import com.pagamento.pix.core.domain.shared.exception.NotFoundException;
import com.pagamento.pix.core.domain.shared.paginates.Paginate;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PixQuery {


    @Autowired
    private final PixRepository pixRepository;
    @Autowired
    private final DozerBeanMapper mapper;


    @Autowired
    public PixQuery(PixRepository pixRepository, DozerBeanMapper mapper) {
        this.pixRepository = pixRepository;
        this.mapper = mapper;
    }



    public List<PixDto> handle(GetPixQuery query) {

        var pixs = getPixs(query);
        if(pixs.isEmpty())
            throw new NotFoundException("Pix not found");
        return pixs.stream()
                .map(pix -> mapper.map(pix, PixDto.class))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private List<Pix> getPixs(GetPixQuery query) {

        if (query.getFilter().getIdUser() != null) return getPixByUser(query);
        if (query.getFilter().getNumberTransaction() != null) return getPixByNumberTransaction(query);
        return getAllPix(query);
    }

    private List<Pix> getPixByUser(GetPixQuery query) {

        var paginate = createPaginate(query);
        return pixRepository.getAll(paginate, query.getUser());
    }

    private List<Pix> getPixByNumberTransaction(GetPixQuery query) {
        var paginate = createPaginate(query);
        return pixRepository.getAll(query.getNumberTransaction(), paginate);

    }

    private List<Pix> getAllPix(GetPixQuery query) {
        var paginate = createPaginate(query);
        return pixRepository.getAll(paginate);
    }
    private static Paginate createPaginate(GetPixQuery query) {

        if (query.getPaginate() == null) {
            query.setPaginate(new PaginateDto("0", "1000"));
        }
        return Paginate.create(query.getPaginate().getOffset(), query.getPaginate().getLimit());
    }
}
