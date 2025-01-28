package com.mybookstore.mybookstore.Dtos;

import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class BookInsertDTO {

    @NotBlank(message = "The title must be specified in the request")
    private String title;
    @NotNull(message = "The author's Id must be specified in the request")
    private Long authorId;
    @NotEmpty(message = "There must be, at least, one genre id specified in the request")
    private List<Long> genreIds;
    @DecimalMin(value = "0.01", message = "The price must by higuer than 0.01")
    private double price;
    @NotBlank(message = "The isbn must be specified in the request")
    private String isbn; 
}
