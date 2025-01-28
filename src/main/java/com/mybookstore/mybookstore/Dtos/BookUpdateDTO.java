package com.mybookstore.mybookstore.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateDTO {
    private String nTitle;
    private double nPrice;
}
