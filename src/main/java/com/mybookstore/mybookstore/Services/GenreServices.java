package com.mybookstore.mybookstore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mybookstore.mybookstore.Dtos.GenreDTO;
import com.mybookstore.mybookstore.Dtos.GenreInsertDTO;
import com.mybookstore.mybookstore.Entities.Genre;
import com.mybookstore.mybookstore.Repositories.GenresRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class GenreServices {

    private GenresRepository genreRepository;

    public GenreDTO addGenre(GenreInsertDTO dto){
        Genre g = new Genre(dto.getNombre());
        genreRepository.save(g);
        return new GenreDTO(g.getId(), g.getTitle());
    }

    public List<GenreDTO> getAllGenre(){
        return genreRepository.findAll()
         .stream().map(g -> new GenreDTO(g.getId(), g.getTitle())).toList();
    }

    public Optional<Genre> getByID(Long id){
        return genreRepository.findById(id);
    }

    public Optional<GenreDTO> updateGenre(Long id, GenreInsertDTO dto){
        Optional<Genre> g = genreRepository.findById(id);
        if(g.isPresent()){
            g.get().setTitle(dto.getNombre());
            genreRepository.save(g.get());
            return Optional.of(new GenreDTO(id, g.get().getTitle()));
        }
        return Optional.empty();
    }

    public Boolean deleteGenre(Long id){
        Optional<Genre> g = genreRepository.findById(id);
        if(g.isPresent()){
            genreRepository.delete(g.get());
            return true;
        }
        return false;
    }


    
}
