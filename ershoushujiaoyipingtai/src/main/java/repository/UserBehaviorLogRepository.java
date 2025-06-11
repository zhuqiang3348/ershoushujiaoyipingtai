package repository;

import com.entity.UserBehaviorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserBehaviorLogRepository extends JpaRepository<UserBehaviorLog, Long> {
    List<UserBehaviorLog> findByUserId(Long userId);

    List<UserBehaviorLog> findTop10ByBehaviorTypeOrderByBehaviorTimeDesc(UserBehaviorLog.BehaviorType type);
}