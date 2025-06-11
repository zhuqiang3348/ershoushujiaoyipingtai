package com.entity;


import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_behavior_log")
@Data
public class UserBehaviorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long bookId;

    @Enumerated(EnumType.STRING)
    private BehaviorType behaviorType;

    private LocalDateTime behaviorTime = LocalDateTime.now();

    public enum BehaviorType {
        view, favorite, order, comment
    }
}