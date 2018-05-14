package com.tbsoares.complain.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Complain {
    @Id
    private String id;
    private String title;
    private String description;
    private String locale;
    private String company;

    public Complain updateAttr(Complain complain){
        return this;
    }
}
