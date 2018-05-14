package com.tbsoares.complain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplainDTO {
    private String id;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String locale;
    @NotNull
    private String company;
}
