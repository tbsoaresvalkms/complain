package com.tbsoares.complain.dto;

import com.tbsoares.complain.util.Utils;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

import static com.tbsoares.complain.util.Optional.optional;

@Getter
public class ComplainQueryParamsDTO implements QueryParams {
    private List<String> id;
    private List<String> title;
    private List<String> description;
    private List<String> locale;
    private List<String> company;
    @Min(0)
    private Integer page = 0;
    @Min(1)
    @Max(100)
    private Integer size = 20;

    public Query getQuery() {
        List<Criteria> criterias = new ArrayList<>();

        optional(id)
                .filter(Utils::notEmpty)
                .map(c -> Criteria.where("id").in(c))
                .ifPresent(criterias::add);

        optional(locale)
                .filter(Utils::notEmpty)
                .map(c -> Criteria.where("locale").in(c))
                .ifPresent(criterias::add);

        optional(company)
                .filter(Utils::notEmpty)
                .map(c -> Criteria.where("company").in(c))
                .ifPresent(criterias::add);

        optional(title)
                .filter(Utils::notEmpty)
                .ifPresent(l -> l.stream()
                        .map(c -> Criteria.where("title").regex(c))
                        .forEach(criterias::add));

        optional(description)
                .filter(Utils::notEmpty)
                .ifPresent(l -> l.stream()
                        .map(c -> Criteria.where("description").regex(c))
                        .forEach(criterias::add));

        Criteria a = criterias
                .stream()
                .reduce(Criteria.where("id").exists(true), Criteria::andOperator);


        return Query.query(a);
    }

    public PageRequest getPageRequest() {
        return PageRequest.of(page, size, Sort.Direction.DESC, "id");
    }
}

