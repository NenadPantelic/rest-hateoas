package com.api.restful.mapper;

import com.api.restful.dto.BookRepresentation;
import com.api.restful.model.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

    public static BookRepresentation mapToRepresentation(Book book) {
        if (book == null) {
            return null;
        }

        return new BookRepresentation(
                book.getId(),
                book.getTitle(),
                book.getSummary(),
                book.getPublishYear(),
                GenreMapper.mapToRepresentationList(book.getGenres()),
                WriterMapper.mapToRepresentationList(book.getWriters())
        );
    }

    public static List<BookRepresentation> mapToRepresentationList(List<Book> books) {
        if (books == null) {
            return null;
        }

        return books.stream()
                .map(BookMapper::mapToRepresentation)
                .collect(Collectors.toList());
    }
}
