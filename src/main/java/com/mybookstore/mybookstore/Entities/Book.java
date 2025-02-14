package com.mybookstore.mybookstore.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
 

@Entity
@Getter @Setter
public class Book { 

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) 
    private Long id;
    private String title;
    private double price;
    private String isbn;
    private int existence;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToMany
    @JoinTable(
        name = "book_genre",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();
    

    public Book() {}

    public Book(String title, Double price, Author author, String isbn) {
        this.title = title;
        this.price = price;
        this.author = author;
        this.isbn = isbn;
    }

    public void addGenre(Genre g){
        genres.add(g);
    }

    public void addExistence(int cantity){
        this.existence += cantity;
    }

    public void reduceExistence(int cantity){
        this.existence -= cantity;
    }


    //Getters y Setters


}
