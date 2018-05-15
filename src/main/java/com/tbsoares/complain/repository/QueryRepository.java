package com.tbsoares.complain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface QueryRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {
    Page<T> findAll(Query query, Pageable pageable);
}
