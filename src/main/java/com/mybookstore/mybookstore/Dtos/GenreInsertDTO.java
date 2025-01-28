package com.mybookstore.mybookstore.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class GenreInsertDTO {
    @NotBlank(message = "El nombre del genero debe ser especificado")
    private String nombre;
}
