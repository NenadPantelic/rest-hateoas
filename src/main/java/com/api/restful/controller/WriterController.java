package com.api.restful.controller;

import com.api.restful.dto.BookRepresentation;
import com.api.restful.dto.WriterRepresentation;
import com.api.restful.service.BookService;
import com.api.restful.service.WriterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Slf4j
@RestController
@RequestMapping("/api/v1/writers")
public class WriterController {

    private final WriterService writerService;
    private final BookService bookService;

    public WriterController(WriterService writerService, BookService bookService) {
        this.writerService = writerService;
        this.bookService = bookService;
    }

    @GetMapping("/{writerId}")
    public WriterRepresentation getWriter(@PathVariable("writerId") int writerId) {
        log.info("Received a request to get writer");
        return writerService.getWriter(writerId);
    }

    @GetMapping(value = "/{writerId}/books", produces = {"application/hal+json"})
    public CollectionModel<BookRepresentation> getBooksForWriter(@PathVariable("writerId") int writerId) {
        List<BookRepresentation> books = bookService.getBooksByWriter(writerId);

        for (BookRepresentation book : books) {
            Link selfLink = linkTo(methodOn(WriterController.class)
                    .getWriter(writerId)).withSelfRel();
            book.add(selfLink);
        }

        Link link = linkTo(methodOn(WriterController.class)
                .getBooksForWriter(writerId)).withSelfRel();
        return CollectionModel.of(books, link);
    }

    @GetMapping(produces = {"application/hal+json"})
    public CollectionModel<WriterRepresentation> getAllWriters() {
        List<WriterRepresentation> writers = writerService.getAll();

        for (WriterRepresentation writer : writers) {
            int writerId = writer.getId();
            Link selfLink = linkTo(WriterController.class).slash(writerId).withSelfRel();
            writer.add(selfLink);

            List<BookRepresentation> books = bookService.getBooksByWriter(writerId);
            if (books != null && !books.isEmpty()) {
                Link booksLink = linkTo(
                        methodOn(WriterController.class).getBooksForWriter(writerId)
                ).withRel("allBooks");
                writer.add(booksLink);
            }
        }

        Link link = linkTo(WriterController.class).withSelfRel();
        return CollectionModel.of(writers, link);
    }
}
