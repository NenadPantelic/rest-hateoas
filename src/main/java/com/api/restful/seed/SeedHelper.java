package com.api.restful.seed;

import com.api.restful.model.Book;
import com.api.restful.model.Genre;
import com.api.restful.model.Writer;
import com.api.restful.repository.BookRepository;
import com.api.restful.repository.GenreRepository;
import com.api.restful.repository.WriterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
public class SeedHelper {

    private static final int NUM_OF_GENRES = 15;
    private static final int NUM_OF_WRITERS = 75;
    private static final int NUM_OF_BOOKS = 10_000;

    private final GenreRepository genreRepository;
    private final WriterRepository writerRepository;
    private final BookRepository bookRepository;

    public SeedHelper(GenreRepository genreRepository,
                      WriterRepository writerRepository,
                      BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.writerRepository = writerRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void generate() {
        log.info("Seeding data...");
        List<Genre> genres = generateGenres();
        List<Writer> writers = generateWriters();
        generateBooks(genres, writers);
        log.info("Successfully generated book data.");
    }

    private List<Genre> generateGenres() {
        log.info("Generating genres...");
        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < NUM_OF_GENRES; i++) {
            genres.add(
                    Genre.builder()
                            .name(generateRandomString(12))
                            .build()
            );
        }

        genreRepository.saveAll(genres);
        return genres;
    }


    private List<Writer> generateWriters() {
        log.info("Generating writers...");
        List<Writer> writers = new ArrayList<>();
        for (int i = 0; i < NUM_OF_WRITERS; i++) {
            writers.add(
                    Writer.builder()
                            .firstName(generateRandomString(6))
                            .lastName(generateRandomString(6))
                            .biography(generateRandomString(20))
                            .yearOfBirth(generateRandomInt(1500, 2006))
                            .build()
            );
        }

        writerRepository.saveAll(writers);
        return writers;
    }

    private void generateBooks(List<Genre> genres, List<Writer> writers) {
        log.info("Generating books...");
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < NUM_OF_BOOKS; i++) {
            Genre genre = genres.get(ThreadLocalRandom.current().nextInt(0, genres.size()));
            Writer writer = writers.get(ThreadLocalRandom.current().nextInt(0, writers.size()));
            Book book = Book.builder()
                    .title(generateRandomString(10))
                    .summary(generateRandomString(150))
                    .publishYear(generateRandomInt(1500, 2024))
                    .build();
            book.addGenre(genre);
            book.addWriter(writer);
            books.add(book);
        }

        bookRepository.saveAll(books);
    }

    // generate random string
    private String generateRandomString(int length) {
        byte[] array = new byte[length];
        ThreadLocalRandom.current().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    private int generateRandomInt(int lowerBound, int upperBound) {
        return ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
    }
}
