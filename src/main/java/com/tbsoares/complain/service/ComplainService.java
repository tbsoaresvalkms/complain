package com.tbsoares.complain.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tbsoares.complain.domain.Complain;
import com.tbsoares.complain.dto.ComplainDTO;
import com.tbsoares.complain.dto.query.QueryParams;
import com.tbsoares.complain.exception.ComplainNotFoundException;
import com.tbsoares.complain.mapper.EntityMapper;
import com.tbsoares.complain.repository.ComplainRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.tbsoares.complain.util.Optional.optional;


@Service
public class ComplainService implements DefaultService<ComplainDTO> {
    private final ComplainRepository repository;
    private final EntityMapper<ComplainDTO, Complain> mapper;

    public ComplainService(ComplainRepository repository, EntityMapper<ComplainDTO, Complain> complainMapper) {
        this.repository = repository;
        this.mapper = complainMapper;
    }


    @Override
    @HystrixCommand
    public ComplainDTO save(ComplainDTO complainDTO) {
        return optional(complainDTO)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    @HystrixCommand
    public ComplainDTO update(ComplainDTO complainDTO) {
        Complain complainToUpdate = mapper.toEntity(complainDTO);

        return optional(complainToUpdate)
                .map(Complain::getId)
                .flatMap(repository::findById)
                .map(c -> c.updateAttr(complainToUpdate))
                .map(repository::save)
                .map(mapper::toDto)
                .orElseThrow(ComplainNotFoundException::new);
    }

    @Override
    @HystrixCommand
    public Page<ComplainDTO> findAll(QueryParams queryParams) {
        return repository.findAll(queryParams.getQuery(), queryParams.getPageRequest())
                .map(mapper::toDto);
    }

    @Override
    @HystrixCommand
    public ComplainDTO findOne(String id) {
        return optional(id)
                .flatMap(repository::findById)
                .map(mapper::toDto)
                .orElseThrow(ComplainNotFoundException::new);
    }

    @Override
    @HystrixCommand
    public void delete(String id) {
        optional(id)
                .filter(repository::existsById)
                .ifPresent(repository::deleteById);
    }
}
