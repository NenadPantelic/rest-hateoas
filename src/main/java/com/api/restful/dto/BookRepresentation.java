package com.api.restful.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class BookRepresentation extends RepresentationModel<BookRepresentation> {

    private String id;
    private String title;
    private String summary;
    private int publishYear;
    private List<GenreRepresentation> genres;
    private List<WriterRepresentation> writers;

}
