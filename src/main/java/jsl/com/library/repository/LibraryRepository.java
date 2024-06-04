package jsl.com.library.repository;

import jsl.com.library.entities.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<LibraryUser, String> {
    @Query("""
            select p from LibraryUser p where p.username = :email
            """)
    Optional<LibraryUser> findByUsername(@Param("email") String email);
    void deleteByUsername(String email);
}
