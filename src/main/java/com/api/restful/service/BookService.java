package com.api.restful.service;

import com.api.restful.dto.BookRepresentation;
import com.api.restful.model.Book;

import java.util.List;

public interface BookService {

    BookRepresentation getBook(String id);

    List<BookRepresentation> getAllBooks();

    List<BookRepresentation> getBooksByWriter(int writerId);
}
