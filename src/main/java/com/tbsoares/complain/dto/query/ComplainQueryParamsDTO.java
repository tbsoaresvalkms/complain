package com.tbsoares.complain.dto.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
public class ComplainQueryParamsDTO implements QueryParams {
    private List<String> id;
    private List<String> locale;
    private List<String> company;
    @Min(0)
    private Integer page = 0;
    @Min(1)
    @Max(100)
    private Integer size = 20;

    public Query getQuery() {
        return new ComplainQueryWrapper(this).getQuery();
    }

    public PageRequest getPageRequest() {
        return PageRequest.of(page, size, Sort.Direction.DESC, "id");
    }
}

