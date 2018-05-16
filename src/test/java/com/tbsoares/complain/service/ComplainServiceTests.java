package com.tbsoares.complain.service;

import com.tbsoares.complain.domain.Complain;
import com.tbsoares.complain.dto.ComplainDTO;
import com.tbsoares.complain.dto.query.ComplainQueryParamsDTO;
import com.tbsoares.complain.exception.ComplainNotFoundException;
import com.tbsoares.complain.mapper.EntityMapper;
import com.tbsoares.complain.repository.ComplainRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;

import static com.tbsoares.complain.util.Optional.optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

public class ComplainServiceTests {

    @Mock
    private ComplainRepository complainRepository;

    @Mock
    private EntityMapper<ComplainDTO, Complain> complainMapper;

    @InjectMocks
    private ComplainService complainService;
    private ComplainDTO complainDTO;
    private Complain complain;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        complainDTO = ComplainDTO.builder()
                .id("123")
                .title("A title")
                .build();

        complain = Complain.builder()
                .id("123")
                .title("A title")
                .build();

        Mockito.when(complainMapper.toDto(complain)).thenReturn(complainDTO);
        Mockito.when(complainMapper.toEntity(complainDTO)).thenReturn(complain);
    }

    @Test
    public void itShouldSaveComplain() {
        Mockito.when(complainRepository.save(complain)).thenReturn(complain);

        ComplainDTO actual = complainService.save(complainDTO);

        assertThat(actual).isEqualToIgnoringNullFields(complainDTO);
        Mockito.verify(complainRepository).save(complain);
    }

    @Test
    public void itShouldUpdateComplain() {
        Mockito.when(complainRepository.save(complain)).thenReturn(complain);
        Mockito.when(complainRepository.findById("123")).thenReturn(optional(complain));

        ComplainDTO actual = complainService.update(complainDTO);

        assertThat(actual).isEqualToIgnoringNullFields(complainDTO);
        Mockito.verify(complainRepository).save(complain);
        Mockito.verify(complainRepository).findById("123");
    }

    @Test
    public void itShouldFindOneComplain() {
        Mockito.when(complainRepository.findById("123")).thenReturn(optional(complain));

        ComplainDTO actual = complainService.findOne("123");

        assertThat(actual.getId()).isEqualToIgnoringCase("123");
        Mockito.verify(complainRepository).findById("123");
        Mockito.verify(complainRepository).findById("123");
    }

    @Test
    public void itShouldDeleteComplain() {
        Mockito.when(complainRepository.existsById("123")).thenReturn(Boolean.TRUE);

        complainService.delete("123");

        Mockito.verify(complainRepository).existsById("123");
        Mockito.verify(complainRepository).deleteById("123");
    }

    @Test(expected = ComplainNotFoundException.class)
    public void itShouldThrowComplainNotFoundWhenFindOneDoesNotExist() {
        complainService.findOne("123");
    }

    @Test(expected = ComplainNotFoundException.class)
    public void itShouldThrowComplainNotFoundWhenUpdateDoesNotExist() {
        complainService.findOne("123");
    }
}
