package com.api.restful.repository;

import com.api.restful.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    @Query(value = "SELECT b.id, b.title, b.summary, b.publish_year, b.created_at, b.updated_at FROM book b, " +
            "book_writer bw WHERE b.id = bw.book_id AND bw.writer_id = :writerId", nativeQuery = true)
    List<Book> findByWriter_id(@Param("writerId") int writerId);
}
