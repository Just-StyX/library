package jsl.com.library.repository;

import jsl.com.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    @Query("""
            SELECT b, u FROM Book b, UserUtilities u WHERE u.bookId = b.id AND u.userId =:userId
            """)
    List<Book> findAllCompletedBooksByUser(@Param("userId") String userId);
}

//SELECT b, FROM Book b innerJoin (SELECT u FROM UserUtilities u WHERE u.userId =:userId HAVING u.isCompleted = true)
