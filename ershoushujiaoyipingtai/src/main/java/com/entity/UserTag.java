package com.entity;



import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "user_tag")
@Data
public class UserTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String tag;

    private Double score = 1.0;
}
