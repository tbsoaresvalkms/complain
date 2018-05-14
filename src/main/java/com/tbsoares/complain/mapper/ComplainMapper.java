package com.tbsoares.complain.mapper;

import com.tbsoares.complain.domain.Complain;
import com.tbsoares.complain.dto.ComplainDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ComplainMapper extends EntityMapper<ComplainDTO, Complain> {
}
