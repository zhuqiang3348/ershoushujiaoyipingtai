package com.controller;

import com.dto.BookRecommendVO;
import com.entity.Book;
import com.entity.BookRecommendation;
import repository.BookRepository;
import com.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recommend")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/{userId}")
    public List<BookRecommendVO> getRecommendations(@PathVariable Long userId) {
        List<BookRecommendation> recs = recommendationService.getUserRecommendations(userId);
        return recs.stream().map(rec -> {
            Book book = bookRepository.findById(rec.getBookId()).orElse(null);
            BookRecommendVO vo = new BookRecommendVO();
            vo.setBookId(rec.getBookId());
            vo.setScore(rec.getScore());
            if (book != null) {
                vo.setTitle(book.getTitle());
                vo.setCover(book.getCover());
            }
            return vo;
        }).collect(Collectors.toList());
    }
}