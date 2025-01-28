package com.mybookstore.mybookstore.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybookstore.mybookstore.Entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

    public Optional<Book> findByTitle(String title);
}
