package com.mybookstore.mybookstore.Dtos;

import jakarta.validation.constraints.NotBlank;

public class AuthorInsertDTO {
    
    @NotBlank(message = "The author´s name must be specifies in the request")
    private String name;
    
    public AuthorInsertDTO( String name) {
        
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
