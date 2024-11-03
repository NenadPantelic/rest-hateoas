package com.api.restful.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class WriterRepresentation extends RepresentationModel<WriterRepresentation> {

    private int id;
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private String biography;
}
