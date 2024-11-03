package com.api.restful.controller;

import com.api.restful.dto.BookRepresentation;
import com.api.restful.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{bookId}")
    public BookRepresentation getBook(@PathVariable("bookId") String bookId) {
        log.info("Received a request to get book");
        BookRepresentation bookRepresentation = bookService.getBook(bookId);
        // self-link
        Link link = linkTo(BookController.class).slash(bookRepresentation.getId()).withSelfRel();
        bookRepresentation.add(link);
        return bookRepresentation;
    }
}
