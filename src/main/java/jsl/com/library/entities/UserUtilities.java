package jsl.com.library.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_utilities")
public class UserUtilities {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "user_id", nullable = false, updatable = false)
    private String userId;

    @Column(name = "book_id", nullable = false, updatable = false)
    private String bookId;

    @Column(name = "started", nullable = false)
    private boolean isStarted = true;

    @Column(name = "current_page", nullable = false)
    private int currentPage = 0;

    @Column(name = "completed")
    private boolean isCompleted;

    @Column(name = "submitted")
    private boolean isSubmitted;

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

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    public UserUtilities userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public UserUtilities bookId(String bookId) {
        this.setBookId(bookId);
        return this;
    }

    public UserUtilities isStarted(boolean isStarted) {
        this.setStarted(isStarted);
        return this;
    }

    public UserUtilities isCompleted(boolean isCompleted) {
        this.setCompleted(isCompleted);
        return this;
    }

    public UserUtilities isSubmitted(boolean isSubmitted) {
        this.setSubmitted(isSubmitted);
        return this;
    }

    public UserUtilities currentPage(int currentPage) {
        this.setCurrentPage(currentPage);
        return this;
    }

    public static UserUtilities init() { return new UserUtilities(); }
}
