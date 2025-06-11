package com.controller;



import com.entity.BookRecommendation;
import com.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recommend")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public List<BookRecommendation> getRecommendations(@PathVariable Long userId) {
        return recommendationService.getUserRecommendations(userId);
    }
}
