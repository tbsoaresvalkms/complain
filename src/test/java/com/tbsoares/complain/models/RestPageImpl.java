package com.tbsoares.complain.models;

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

}
