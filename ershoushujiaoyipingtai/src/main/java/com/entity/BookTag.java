package com.entity;



import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "book_tag")
@Data
public class BookTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;

    private String tag;
}