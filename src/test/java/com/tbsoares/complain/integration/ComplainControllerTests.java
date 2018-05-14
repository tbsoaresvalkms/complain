package com.tbsoares.complain.integration;

import com.tbsoares.complain.domain.Complain;
import com.tbsoares.complain.dto.ComplainDTO;
import com.tbsoares.complain.mapper.EntityMapper;
import com.tbsoares.complain.repository.ComplainRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ComplainControllerTests extends ApplicationControllerTest {

    final String URL = "/complain";

    @Autowired
    ComplainRepository repository;

    @Autowired
    EntityMapper<ComplainDTO, Complain> complainMapper;

    @Before
    public void init() {
        repository.deleteAll();
    }
}
