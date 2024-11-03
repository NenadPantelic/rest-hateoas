package com.api.restful.mapper;

import com.api.restful.dto.GenreRepresentation;
import com.api.restful.model.Genre;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GenreMapper {

    public static GenreRepresentation mapToRepresentation(Genre genre) {
        if (genre == null) {
            return null;
        }

        return new GenreRepresentation(genre.getId(), genre.getName());

    }

    public static List<GenreRepresentation> mapToRepresentationList(Collection<Genre> genres) {
        if (genres == null) {
            return null;
        }

        return genres.stream()
                .map(GenreMapper::mapToRepresentation)
                .collect(Collectors.toList());
    }
}
