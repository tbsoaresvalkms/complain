package com.tbsoares.complain.service;

import com.tbsoares.complain.dto.QueryParams;

import java.util.List;

public interface DefaultService<T> {
    T save(T t);

    T update(T t);

    List<T> findAll(QueryParams queryParams);

    T findOne(String id);

    void delete(String id);
}
