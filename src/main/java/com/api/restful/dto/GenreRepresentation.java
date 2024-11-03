package com.api.restful.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class GenreRepresentation extends RepresentationModel<GenreRepresentation> {

    private int id;
    private String name;
}
