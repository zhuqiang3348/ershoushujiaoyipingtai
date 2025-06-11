package com.entity;



import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_recommendation")
@Data
public class BookRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long bookId;
    private Double score;
    private LocalDateTime recommendTime = LocalDateTime.now();
}