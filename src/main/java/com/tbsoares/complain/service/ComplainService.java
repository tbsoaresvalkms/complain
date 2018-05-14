package com.tbsoares.complain.service;


import com.tbsoares.complain.domain.Complain;
import com.tbsoares.complain.dto.ComplainDTO;
import com.tbsoares.complain.exception.ComplainNotFoundException;
import com.tbsoares.complain.mapper.EntityMapper;
import com.tbsoares.complain.repository.ComplainRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public ComplainDTO save(ComplainDTO complainDTO) {
        return optional(complainDTO)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::toDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
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
    public List<ComplainDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ComplainDTO findOne(String id) {
        return optional(id)
                .flatMap(repository::findById)
                .map(mapper::toDto)
                .orElseThrow(ComplainNotFoundException::new);
    }

    @Override
    public void delete(String id) {
        optional(id)
                .filter(repository::existsById)
                .ifPresent(repository::deleteById);
    }
}
