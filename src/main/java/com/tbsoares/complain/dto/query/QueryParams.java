package com.tbsoares.complain.dto.query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;

public interface QueryParams {
    Query getQuery();

    PageRequest getPageRequest();
}
