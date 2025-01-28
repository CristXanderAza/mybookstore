package com.mybookstore.mybookstore.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybookstore.mybookstore.Entities.Book;
import com.mybookstore.mybookstore.Entities.Genre;

public interface GenresRepository extends JpaRepository<Genre, Long>{

    public List<Book> findByTitleContainingIgnoreCase(String title);
}
