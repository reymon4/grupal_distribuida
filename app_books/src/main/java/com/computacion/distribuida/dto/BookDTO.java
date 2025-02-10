package com.computacion.distribuida.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDTO {
    private Integer id;
    private String isbn;
    private String title;
    private BigDecimal price;
    private String authorName;
}