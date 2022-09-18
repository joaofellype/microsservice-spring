package com.pagamento.pix.infrastructure.persistence.mongo;

import com.pagamento.pix.core.domain.shared.paginates.Paginate;
import com.pagamento.pix.core.domain.shared.validators.ListValidator;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class QueryMongoDbHelper {

    private QueryMongoDbHelper() {
        throw new IllegalArgumentException();
    }

    public static void addAndCriteria(Query query, String value, String propertyName) {
        query.addCriteria(Criteria.where(propertyName).is(value));
    }

    public static void addInCriteria(Query query, List<String> values, String propertyName) {
        if (!ListValidator.isEmpty(values))
            query.addCriteria(Criteria.where(propertyName).in(values));
    }

    public static void addAscSort(Query query, String propertyName) {
        query.with(Sort.by(Sort.Direction.ASC, propertyName));
    }

    public static void addInListCriteria(Query query, List<String> items, String listPropertyName, String propertyName) {
        if (!ListValidator.isEmpty(items))
            query.addCriteria(Criteria.where(listPropertyName).elemMatch(Criteria.where(propertyName).in(items)));
    }

    public static void addPaginateCriteria(Query query, Paginate paginate) {
        query.skip(paginate.getOffset());
        query.limit(paginate.getLimit());
    }

    public static void addStakeholdersCriteria(Query query, List<String> stakeholderIds) {
        addInListCriteria(query, stakeholderIds, "stakeholders", "internalId");
    }
}
