package com.mybookstore.mybookstore.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybookstore.mybookstore.Entities.Author;


public interface AuthorRepository extends JpaRepository<Author, Long>{

   public Optional<Author> findByName(String name);
}
  