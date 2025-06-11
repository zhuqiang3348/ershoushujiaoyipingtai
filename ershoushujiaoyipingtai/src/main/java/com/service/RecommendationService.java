package com.service;


import com.entity.BookRecommendation;
import com.entity.UserBehaviorLog;
import repository.BookRecommendationRepository;
import repository.UserBehaviorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationService {

    @Autowired
    private UserBehaviorLogRepository userBehaviorLogRepository;

    @Autowired
    private BookRecommendationRepository bookRecommendationRepository;

    // 热门推荐
    public List<Long> getHotBooks(int limit) {
        List<UserBehaviorLog> favorites = userBehaviorLogRepository.findTop10ByBehaviorTypeOrderByBehaviorTimeDesc(UserBehaviorLog.BehaviorType.favorite);
        Set<Long> bookIds = new LinkedHashSet<>();
        for (UserBehaviorLog log : favorites) {
            bookIds.add(log.getBookId());
            if (bookIds.size() >= limit) break;
        }
        return new ArrayList<>(bookIds);
    }

    // 个性化推荐
    public List<BookRecommendation> getUserRecommendations(Long userId) {
        List<BookRecommendation> result = bookRecommendationRepository.findByUserIdOrderByScoreDesc(userId);
        if (result.isEmpty()) {
            // 没有个性化推荐时返回热门
            List<Long> hotBooks = getHotBooks(10);
            List<BookRecommendation> hotRecs = new ArrayList<>();
            for (Long bookId : hotBooks) {
                BookRecommendation rec = new BookRecommendation();
                rec.setUserId(userId);
                rec.setBookId(bookId);
                rec.setScore(1.0);
                hotRecs.add(rec);
            }
            return hotRecs;
        }
        return result;
    }

    // 推荐生成算法
    public void generateRecommendationsForAllUsers(List<Long> userIds) {
        for (Long userId : userIds) {
            List<UserBehaviorLog> myLogs = userBehaviorLogRepository.findByUserId(userId);
            Set<Long> myBookIds = new HashSet<>();
            for (UserBehaviorLog log : myLogs) {
                myBookIds.add(log.getBookId());
            }
            Map<Long, Integer> candidateBookCount = new HashMap<>();
            for (UserBehaviorLog log : userBehaviorLogRepository.findAll()) {
                if (!log.getUserId().equals(userId) && myBookIds.contains(log.getBookId())) {
                    List<UserBehaviorLog> hisLogs = userBehaviorLogRepository.findByUserId(log.getUserId());
                    for (UserBehaviorLog hlog : hisLogs) {
                        if (!myBookIds.contains(hlog.getBookId())) {
                            candidateBookCount.put(hlog.getBookId(),
                                    candidateBookCount.getOrDefault(hlog.getBookId(), 0) + 1);
                        }
                    }
                }
            }
            List<Map.Entry<Long, Integer>> sorted = new ArrayList<>(candidateBookCount.entrySet());
            sorted.sort((a, b) -> b.getValue() - a.getValue());
            List<BookRecommendation> recs = new ArrayList<>();
            for (int i = 0; i < Math.min(10, sorted.size()); i++) {
                BookRecommendation rec = new BookRecommendation();
                rec.setUserId(userId);
                rec.setBookId(sorted.get(i).getKey());
                rec.setScore(sorted.get(i).getValue().doubleValue());
                recs.add(rec);
            }
            bookRecommendationRepository.saveAll(recs);
        }
    }
}
