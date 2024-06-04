package jsl.com.library.repository;

import jsl.com.library.entities.BookUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookUsersRepository extends JpaRepository<BookUsers, String> {
    @Query("""
            SELECT b FROM BookUsers b WHERE b.userId =:userId AND b.bookId =:bookId
            """)
    BookUsers findByUserIdAndBookId(@Param("userId") String userId, @Param("bookId") String bookId);
}
