package com.mybookstore.mybookstore.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybookstore.mybookstore.Dtos.AuthorDTO;
import com.mybookstore.mybookstore.Dtos.AuthorInsertDTO;
import com.mybookstore.mybookstore.Services.AuthorServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
 
    private AuthorServices authorServices;

    public AuthorController(AuthorServices authorServices) {
        this.authorServices = authorServices;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAll(){
        return ResponseEntity.ok(authorServices.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        return authorServices.getByID(id)
            .map(a -> ResponseEntity.ok(a)) 
            .orElse(ResponseEntity.notFound().build()); 
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> addBook(@Valid @RequestBody AuthorInsertDTO dto){
        AuthorDTO res = authorServices.addAuthor(dto);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorInsertDTO dto){
        return authorServices.update(id, dto)
            .map(a -> ResponseEntity.ok(a))
            .orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        Boolean b =  authorServices.deleteAuthor(id);
        return (b)? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

}
