package com.tbsoares.complain.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

public class ComplainQueryParamsDTO {

    public Query getQuery() {
        return new Query();
    }

    public PageRequest getPageRequest() {
        return PageRequest.of(10, 100, Sort.Direction.ASC);
    }
}
