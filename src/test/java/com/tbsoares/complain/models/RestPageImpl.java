package com.tbsoares.complain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class RestPageImpl<T> {
    private List<T> content;
    private int page;
    private int size;
    private long total;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestPageImpl(@JsonProperty("content") List<T> content,
                        @JsonProperty("number") int page,
                        @JsonProperty("size") int size,
                        @JsonProperty("totalElements") long total) {

        this.content = content;
        this.page = page;
        this.size = size;
        this.total = total;
    }
}
