package com.mybookstore.mybookstore.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybookstore.mybookstore.Dtos.GenreDTO;
import com.mybookstore.mybookstore.Dtos.GenreInsertDTO;
import com.mybookstore.mybookstore.Entities.Genre;
import com.mybookstore.mybookstore.Services.GenreServices;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;  
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private GenreServices genreService;

    public GenreController(GenreServices genreServices){
        this.genreService = genreServices; 
    }

    @GetMapping
    public List<GenreDTO> getAllGenres(){
        return genreService.getAllGenre();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        return genreService.getByID(id).map(e-> ResponseEntity.ok(e))
        .orElse(ResponseEntity.notFound().build());
    }
    

    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreInsertDTO insertDTO){
        return ResponseEntity.ok(genreService.addGenre(insertDTO));        
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable Long id, @RequestBody GenreInsertDTO entity) {
        return genreService.updateGenre(id, entity)
        .map(r -> ResponseEntity.ok(r)) 
        .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGenre(@PathVariable Long id){
        Boolean b = genreService.deleteGenre(id);
        if(b){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

}
 