package jsl.com.library.repository;

import jsl.com.library.entities.UserUtilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserUtilitiesRepository extends JpaRepository<UserUtilities, String> {
    @Query("""
            SELECT u FROM UserUtilities u WHERE u.userId =:userId AND u.bookId =:bookId
            """)
    UserUtilities findByUserIdAndBookId(@Param("userId") String userId, @Param("bookId") String bookId);

    @Query("""
            SELECT u FROM UserUtilities u WHERE u.userId =:userId
            """)
    List<UserUtilities> findByUserId(@Param("userId") String userId);
}
