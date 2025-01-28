package com.mybookstore.mybookstore.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mybookstore.mybookstore.Dtos.BookDTO;
import com.mybookstore.mybookstore.Dtos.BookInsertDTO;
import com.mybookstore.mybookstore.Dtos.BookUpdateDTO;
import com.mybookstore.mybookstore.Entities.Author;
import com.mybookstore.mybookstore.Entities.Book;
import com.mybookstore.mybookstore.Entities.Genre;
import com.mybookstore.mybookstore.Repositories.AuthorRepository;
import com.mybookstore.mybookstore.Repositories.BookRepository;
import com.mybookstore.mybookstore.Repositories.GenresRepository; 
import com.mybookstore.mybookstore.errors.AuthorNotFoundException;  

@Service
public class BookServices { 

    private AuthorRepository authorRepository;
    private BookRepository  bookRepository;
    private GenresRepository genresRepository;

    public BookServices(BookRepository bookRepository, AuthorRepository authorRepository, GenresRepository genresRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genresRepository = genresRepository;
    }
    
    public Optional<BookDTO> getBookByID(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(!book.isPresent())
            return Optional.empty();
        List<String> genreStrings = book.get().getGenres().stream().map( b -> (String) b.getTitle()).toList();
        return  book.map(b -> new BookDTO(b.getId(), b.getTitle(), b.getAuthor().getName(), genreStrings, b.getPrice()));
    }

    public Optional<BookDTO> getBookByTitle(String title){
       return mapBookToDTO(bookRepository.findByTitle(title));
    }

    public List<BookDTO> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        List<BookDTO> dtos = new ArrayList<>();
        for (Book book : books) {
            List<String> genreStrings = book.getGenres().stream().map( b -> (String) b.getTitle()).toList();
            dtos.add(new BookDTO(book.getId(), book.getTitle(), book.getAuthor().getName(), genreStrings, book.getPrice())); 
        }
        return dtos;
    }

    public BookDTO createNewBook(BookInsertDTO dtoInsert){
        Optional<Author> author = authorRepository.findById(dtoInsert.getAuthorId());
        if(!author.isPresent()){
            return null;
        }
        List<Genre> genres = new ArrayList<>();
        for (Long genreId : dtoInsert.getGenreIds()) {
            Optional<Genre> g = genresRepository.findById(genreId);
            if(!g.isPresent()){
               throw new AuthorNotFoundException(dtoInsert.getAuthorId());
            }
            genres.add(g.get());
        }
        
        Book nBook = new Book(dtoInsert.getTitle(), dtoInsert.getPrice(), author.get(), dtoInsert.getIsbn());
        genres.stream().forEach(ge -> nBook.addGenre(ge));
        List<String> genresTitles = genres.stream().map(gen -> (String) gen.getTitle()).toList();
        bookRepository.save(nBook);
        return new BookDTO(nBook.getId(), nBook.getTitle(), nBook.getAuthor().getName(), genresTitles, nBook.getPrice());

    }

    public Optional<BookDTO> updateBook(Long id, BookUpdateDTO updateDTO){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            Book b = book.get();
            b.setTitle(updateDTO.getNTitle());
            b.setPrice(updateDTO.getNPrice());
            bookRepository.save(b);
            return mapBookToDTO(book);
        }
        return Optional.empty();
    }

    public Boolean deleteBook(Long id){
        var b = bookRepository.findById(id);
        if(b.isPresent()){
            bookRepository.delete(b.get());
        }
        return b.isPresent();
    }

    private Optional<BookDTO> mapBookToDTO(Optional<Book> book){
        if(!book.isPresent())
            return Optional.empty();
        List<String> genreStrings = book.get().getGenres().stream().map( b -> (String) b.getTitle()).toList();
        return  book.map(b -> new BookDTO(b.getId(), b.getTitle(), b.getAuthor().getName(), genreStrings, b.getPrice()));
    }

    private BookDTO mapBookToDTO(Book book){
        List<String> genreStrings = book.getGenres().stream().map( b -> (String) b.getTitle()).toList();
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor().getName(), genreStrings, book.getPrice());
    }

    public List<BookDTO> getBooksByAuthor(Long authorID){
        Optional<Author> author = authorRepository.findById(authorID);
        if(author.isPresent()){
            return author.get().getBooks().stream()
            .map(b -> mapBookToDTO(b)).toList();
        }
        return new ArrayList<>();
    }

    public List<BookDTO> getBooksByGenre(Long genreID){
        Optional<Genre> genre = genresRepository.findById(genreID);
        if(genre.isPresent()){
            return genre.get().getBooks().stream()
            .map(b -> mapBookToDTO(b)).toList();
            
        }
        return new ArrayList<>();
    }


}
