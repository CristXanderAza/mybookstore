package com.mybookstore.mybookstore.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class GenreDTO {
    private Long id;
    private String nombre;
}
