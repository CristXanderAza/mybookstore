package com.mybookstore.mybookstore.Dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class BookDTO {

    public Long id;
    public String title;
    public String authorName;
    public List<String> genres;
    public double price;
}
