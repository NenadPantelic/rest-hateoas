package com.api.restful.mapper;

import com.api.restful.dto.GenreRepresentation;
import com.api.restful.dto.WriterRepresentation;
import com.api.restful.model.Genre;
import com.api.restful.model.Writer;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class WriterMapper {

    public static WriterRepresentation mapToRepresentation(Writer writer) {
        if (writer == null) {
            return null;
        }

        return new WriterRepresentation(
                writer.getId(),
                writer.getFirstName(),
                writer.getLastName(),
                writer.getYearOfBirth(),
                writer.getBiography()
        );
    }

    public static List<WriterRepresentation> mapToRepresentationList(Collection<Writer> writers) {
        if (writers == null) {
            return null;
        }

        return writers.stream()
                .map(WriterMapper::mapToRepresentation)
                .collect(Collectors.toList());
    }
}
