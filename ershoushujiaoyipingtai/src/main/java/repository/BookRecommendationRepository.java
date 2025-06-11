package repository;


import com.entity.BookRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRecommendationRepository extends JpaRepository<BookRecommendation, Long> {
    List<BookRecommendation> findByUserIdOrderByScoreDesc(Long userId);
}
