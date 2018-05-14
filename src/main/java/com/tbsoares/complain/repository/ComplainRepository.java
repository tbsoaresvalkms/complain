package com.tbsoares.complain.repository;


import com.tbsoares.complain.domain.Complain;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComplainRepository extends MongoRepository<Complain, String> {
}
