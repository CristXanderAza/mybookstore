package com.mybookstore.mybookstore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mybookstore.mybookstore.Dtos.AuthorDTO;
import com.mybookstore.mybookstore.Dtos.AuthorInsertDTO;
import com.mybookstore.mybookstore.Entities.Author;
import com.mybookstore.mybookstore.Repositories.AuthorRepository; 

@Service
public class AuthorServices {

    private AuthorRepository authorRepository;

    public AuthorServices(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> getAll(){
            return authorRepository.findAll().stream()
                    .map(a -> new AuthorDTO(a.getId(), a.getName()))
                    .toList();
        
    }


    public Optional<AuthorDTO> getByID(Long id){
        return authorRepository.findById(id).map(a -> new AuthorDTO(a.getId(), a.getName()));
    }

    public AuthorDTO addAuthor(AuthorInsertDTO dto){
        Author a = new Author(dto.getName());
        authorRepository.save(a);
        return new AuthorDTO(a.getId(), a.getName());
    }   

    public Optional<AuthorDTO> update(Long id, AuthorInsertDTO dto){
        Author a = authorRepository.findById(id).get();
        a.setName(dto.getName());
        authorRepository.save(a);
        return Optional.of(new AuthorDTO(id, dto.getName()));
    }    

    public Boolean deleteAuthor(Long id){
        var a = authorRepository.getById(id);
        if(a != null){
            authorRepository.delete(a);
            return true;
        }
        else{
            return false;
        }
        
    }
}
