package com.tbsoares.complain.dto.query;

import com.tbsoares.complain.util.Utils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

import static com.tbsoares.complain.util.Optional.optional;

class ComplainQueryWrapper {
    private final ComplainQueryParamsDTO queryParamsDTO;

    public ComplainQueryWrapper(ComplainQueryParamsDTO queryParamsDTO) {
        this.queryParamsDTO = queryParamsDTO;
    }

    public Query getQuery() {
        List<Criteria> criterias = new ArrayList<>();

        optional(queryParamsDTO.getId())
                .filter(Utils::notEmpty)
                .map(c -> Criteria.where("id").in(c))
                .ifPresent(criterias::add);

        optional(queryParamsDTO.getLocale())
                .filter(Utils::notEmpty)
                .map(c -> Criteria.where("locale").in(c))
                .ifPresent(criterias::add);

        optional(queryParamsDTO.getCompany())
                .filter(Utils::notEmpty)
                .map(c -> Criteria.where("company").in(c))
                .ifPresent(criterias::add);

        Criteria identify = Criteria.where("id").exists(true);

        optional(criterias)
                .filter(Utils::notEmpty)
                .map(c -> c.toArray(new Criteria[c.size()]))
                .ifPresent(identify::andOperator);

        return Query.query(identify);
    }
}

