package com.tbsoares.complain.service;

import java.util.List;

public interface DefaultService<T> {
    T save(T t);

    T update(T t);

    List<T> findAll();

    T findOne(String id);

    void delete(String id);
}
