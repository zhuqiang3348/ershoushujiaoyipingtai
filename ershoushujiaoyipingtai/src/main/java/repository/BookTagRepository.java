package repository;


import com.entity.BookTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookTagRepository extends JpaRepository<BookTag, Long> {
    List<BookTag> findByBookId(Long bookId);
    List<BookTag> findByTag(String tag);
}
