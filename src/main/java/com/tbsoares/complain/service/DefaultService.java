package com.tbsoares.complain.service;

import com.tbsoares.complain.dto.query.QueryParams;
import org.springframework.data.domain.Page;

public interface DefaultService<T> {
    T save(T t);

    T update(T t);

    Page<T> findAll(QueryParams queryParams);

    T findOne(String id);

    void delete(String id);
}
