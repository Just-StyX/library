package jsl.com.library.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_users")
public class BookUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "user_id", nullable = false, updatable = false)
    private String userId;

    @Column(name = "book_id", nullable = false, updatable = false)
    private String bookId;

    @Column(name = "borrowed_on", nullable = false, updatable = false)
    private LocalDateTime borrowedOn;

    @Column(name = "is_submitted", nullable = false)
    private boolean isSubmitted = false;

    @Column(name = "submitted_on")
    private LocalDateTime submittedOn;

    public BookUsers userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public BookUsers bookId(String bookId) {
        this.setBookId(bookId);
        return this;
    }

    public BookUsers borrowedDate() {
        this.setBorrowedOn(LocalDateTime.now());
        return this;
    }

    public BookUsers submittedDate() {
        this.setSubmittedOn(LocalDateTime.now());
        return this;
    }

    public BookUsers submitted(boolean submitted) {
        this.setSubmitted(submitted);
        return this;
    }

    public static BookUsers init() { return new BookUsers(); }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getBorrowedOn() {
        return borrowedOn;
    }

    public void setBorrowedOn(LocalDateTime borrowedOn) {
        this.borrowedOn = borrowedOn;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    public LocalDateTime getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(LocalDateTime submittedOn) {
        this.submittedOn = submittedOn;
    }
}
