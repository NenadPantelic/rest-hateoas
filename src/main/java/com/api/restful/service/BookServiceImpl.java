package com.api.restful.service;

import com.api.restful.dto.BookRepresentation;
import com.api.restful.mapper.BookMapper;
import com.api.restful.model.Book;
import com.api.restful.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookRepresentation getBook(String id) {
        log.info("Get book by id = {}", id);
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Book[id = %s] not found.", id))
        );

        return BookMapper.mapToRepresentation(book);
    }

    @Override
    public List<BookRepresentation> getAllBooks() {
        log.info("Get all books...");
        return BookMapper.mapToRepresentationList(bookRepository.findAll());
    }

    @Override
    public List<BookRepresentation> getBooksByWriter(int writerId) {
        log.info("Get books by writer with id {}", writerId);
        return BookMapper.mapToRepresentationList(bookRepository.findByWriter_id(writerId));
    }
}
