package com.pagamento.pix.core.queries;

import com.pagamento.pix.core.domain.aggregates.pix.PixRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PixQueryImpl {

    private final PixRepository pixRepository;
    private final DozerBeanMapper mapper;

    private PixQuery pixQuery;

    @Autowired
    public PixQueryImpl(PixRepository pixRepository, DozerBeanMapper mapper, PixQuery pixQuery) {
        this.pixRepository = pixRepository;
        this.mapper = mapper;
        this.pixQuery = new PixQuery(pixRepository, mapper);
    }

    public PixQuery getPixQuery() {
        return pixQuery;
    }
}
