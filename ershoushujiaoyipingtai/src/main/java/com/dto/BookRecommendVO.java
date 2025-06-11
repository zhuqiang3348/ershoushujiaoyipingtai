package com.dto;

import lombok.Data;

@Data
public class BookRecommendVO {
    private Long bookId;
    private String title;
    private String cover;
    private Double score;
}