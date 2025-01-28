package com.mybookstore.mybookstore.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybookstore.mybookstore.Dtos.BookDTO;
import com.mybookstore.mybookstore.Dtos.BookInsertDTO;
import com.mybookstore.mybookstore.Dtos.BookUpdateDTO; 
import com.mybookstore.mybookstore.Services.BookServices;
 
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
               
@RestController
@RequestMapping("api/book")
@AllArgsConstructor
public class BookController {

    private BookServices bookServices;

    @GetMapping
    public ResponseEntity<List<BookDTO>> geBookDTOs(){
        return ResponseEntity.ok(bookServices.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getByID(@PathVariable Long id){
        return bookServices.getBookByID(id)
        .map(b -> ResponseEntity.ok(b))
        .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/ofAuthor/{authorID}")
    public ResponseEntity<List<BookDTO>> getByAuthor(@PathVariable Long authorID){
        return ResponseEntity.ok(bookServices.getBooksByAuthor(authorID));
    }

    @GetMapping("/ofGenre/{genreID}")
    public ResponseEntity<List<BookDTO>> getByGenre(@PathVariable Long genreID){
        return ResponseEntity.ok(bookServices.getBooksByGenre(genreID));
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookInsertDTO dto){
        BookDTO b = bookServices.createNewBook(dto);
        if(b == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(b);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookUpdateDTO dto){
        return bookServices.updateBook(id, dto)
        .map(b -> ResponseEntity.ok(b))
        .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        Boolean b = bookServices.deleteBook(id);
        if(b){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
    
}
