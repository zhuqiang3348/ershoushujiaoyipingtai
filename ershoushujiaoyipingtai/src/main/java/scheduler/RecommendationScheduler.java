package scheduler;


import com.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class RecommendationScheduler {

    @Autowired
    private RecommendationService recommendationService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void refreshRecommendations() {
        // 示例，实际应查询用户表
        List<Long> allUserIds = Arrays.asList(1L, 2L, 3L);
        recommendationService.generateRecommendationsForAllUsers(allUserIds);
    }
}
